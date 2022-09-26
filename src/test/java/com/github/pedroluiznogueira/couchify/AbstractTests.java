package com.github.pedroluiznogueira.couchify;

import lombok.Getter;
import org.junit.jupiter.api.AfterAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.couchbase.CouchbaseContainer;
import org.testcontainers.shaded.org.awaitility.Awaitility;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AbstractTests {

    @Getter
    private static final CouchbaseContainer couchbaseContainer = new CouchbaseContainer(Couchbase.COUCHBASE_IMAGE)
            .withCredentials(Couchbase.USERNAME, Couchbase.PASSWORD)
            .withBucket(Couchbase.BUCKET_DEFINITION)
            .withStartupTimeout(Duration.ofSeconds(10))
            .waitingFor(Wait.forHealthcheck());

    @AfterAll
    public static void stopContainer() {
        couchbaseContainer.stop();
    }

    @DynamicPropertySource
    private static void bindCouchbaseProperties(final DynamicPropertyRegistry registry) {
        // starts the container
        couchbaseContainer.start();
        // wait until the couchbase container is running
        Awaitility.await()
                .until(couchbaseContainer::isRunning);
        // override default ports
        final int bootstrapHttpSslPort = couchbaseContainer.getMappedPort(18091);
        final int bootstrapCarrierSslPort = couchbaseContainer.getMappedPort(11207);
        // override default properties from the couchbase container
        registry.add("couchbase.cluster1", couchbaseContainer::getContainerIpAddress);
        registry.add("couchbase.bootstrapHttpDirectPort", couchbaseContainer::getBootstrapHttpDirectPort);
        registry.add("couchbase.bootstrapHttpSslPort", () -> bootstrapHttpSslPort);
        registry.add("couchbase.bootstrapCarrierDirectPort", couchbaseContainer::getBootstrapCarrierDirectPort);
        registry.add("couchbase.bootstrapCarrierSslPort", () -> bootstrapCarrierSslPort);
        registry.add("couchbase.bucket.usersession.name", couchbaseContainer::getUsername);
        registry.add("couchbase.bucket.usersession.password", couchbaseContainer::getPassword);
        registry.add("couchbase.bucket.configuration.name", couchbaseContainer::getUsername);
        registry.add("couchbase.bucket.configuration.password", couchbaseContainer::getPassword);
        registry.add("couchbase.bucket.bucketOpenTimeout", () -> 250000);
        registry.add("couchbase.bucket.operationTimeout", () -> 600000);
        registry.add("couchbase.bucket.observableTimeoutMilliSeconds", () -> 650000);
        registry.add("couchbase.bucket.ioPoolSize", () -> 3);
        registry.add("couchbase.bucket.computationPoolSize", () -> 3);
    }
}