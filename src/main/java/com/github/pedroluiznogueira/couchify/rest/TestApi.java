package com.github.pedroluiznogueira.couchify.rest;

import com.github.pedroluiznogueira.couchify.entity.TestEntity;
import com.github.pedroluiznogueira.couchify.repository.TestRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestApi {

    private final TestRepository testRepository;

    public TestApi(final TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping
    public List<TestEntity> test() {
        testRepository.save(new TestEntity(RandomString.make(10), "field"));
        return testRepository.findAll();
    }
}