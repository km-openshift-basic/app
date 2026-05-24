package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body("message", is("Hello from Test!"));
    }

    @Test
    void testInfoEndpoint() {
        given()
                .when().get("/info")
                .then()
                .statusCode(200)
                .body("application", is("openshift-workshop-app"))
                .body("version", is("1.0.0"))
                .body("environment", is("test"))
                .body("javaVersion", notNullValue())
                .body("hostname", notNullValue());
    }
}
