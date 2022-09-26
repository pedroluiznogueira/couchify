package com.github.pedroluiznogueira.couchify.repository;

import com.github.pedroluiznogueira.couchify.entity.TestEntity;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface TestRepository extends CouchbaseRepository<TestEntity, String> {
}