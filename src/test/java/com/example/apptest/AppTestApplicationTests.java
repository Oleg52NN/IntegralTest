package com.example.apptest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppTestApplicationTests {


    @Autowired
    TestRestTemplate restTemplate;
    @Container
    private static final GenericContainer<?> myAppOne= new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> myAppTwo= new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);


    @Test
    void testDev() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + myAppOne.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals("Current profile is dev", entity.getBody());
    }

    @Test
    void testProd() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + myAppTwo.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals("Current profile is production", entity.getBody());
    }


}

