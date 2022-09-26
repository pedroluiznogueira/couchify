package com.github.pedroluiznogueira.couchify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Data
@AllArgsConstructor
@Document
public class TestEntity {
    @Id
    private String id;
    @Field
    private String field;
}