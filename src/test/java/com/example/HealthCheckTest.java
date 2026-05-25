package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class HealthCheckTest {

    @Test
    void testLivenessEndpoint() {
        given()
                .when().get("/health/live")
                .then()
                .statusCode(200)
                .body("status", is("UP"));
    }

    @Test
    void testReadinessEndpoint() {
        given()
                .when().get("/health/ready")
                .then()
                .statusCode(200)
                .body("status", is("UP"));
    }

    @Test
    void testHealthEndpoint() {
        given()
                .when().get("/health")
                .then()
                .statusCode(200)
                .body("status", is("UP"));
    }
}
