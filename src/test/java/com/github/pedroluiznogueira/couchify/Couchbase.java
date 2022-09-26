package com.github.pedroluiznogueira.couchify;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.utility.DockerImageName;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Couchbase {
    private static final String BUCKET_NAME = "SportsStats";

    public static final String USERNAME = "Administrator";
    public static final String PASSWORD = "Password";
    public static final BucketDefinition BUCKET_DEFINITION = new BucketDefinition(BUCKET_NAME);
    public static final DockerImageName COUCHBASE_IMAGE = DockerImageName.parse("couchbase:enterprise")
            .asCompatibleSubstituteFor("couchbase/server")
            .withTag("6.0.1");
}