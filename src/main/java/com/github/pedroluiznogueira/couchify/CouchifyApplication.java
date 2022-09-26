package com.github.pedroluiznogueira.couchify;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CouchifyApplication {

	@Value("${couchbase.clusterHost}")
	private String hostname;

	@Value("${couchbase.username}")
	private String username;

	@Value("${couchbase.password}")
	private String password;

	@Value("${couchbase.bucket}")
	private String bucket;

	public static void main(String... args) {
		SpringApplication.run(CouchifyApplication.class, args);
	}

	@Bean
	public Cluster cluster() {
		return Cluster.connect(hostname, username, password);
	}

	@Bean
	public Bucket couchmusicBucket(final Cluster cluster) {
		return cluster.bucket(bucket);
	}
}
