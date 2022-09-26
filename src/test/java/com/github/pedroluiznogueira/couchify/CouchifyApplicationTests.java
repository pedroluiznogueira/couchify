package com.github.pedroluiznogueira.couchify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.couchbase.CouchbaseContainer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CouchifyApplicationTests extends AbstractTests {

	@Test
	public void contextLoads() {
		assertThat(getCouchbaseContainer())
				.extracting(CouchbaseContainer::isRunning)
				.isEqualTo(Boolean.TRUE);
	}
}
