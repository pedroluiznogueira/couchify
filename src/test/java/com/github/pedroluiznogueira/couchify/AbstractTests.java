package com.github.pedroluiznogueira.couchify;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class AbstractTests {

    @Getter
    @Autowired
    public Cluster cluster;

    @Getter
    @Autowired
    public Bucket bucket;

}