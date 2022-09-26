package com.github.pedroluiznogueira.couchify;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CouchifyApplication {

	public static void main(String... args) {
		SpringApplication.run(CouchifyApplication.class, args);

		// connect to couchbase cluster
		final Cluster cluster = Cluster.connect("localhost", "cbuser", "password");

		// obtain bucket
		final Bucket bucket = cluster.bucket("couchmusic2");

		// obtain collection
		final Collection collection = bucket.defaultCollection();
	}
}
