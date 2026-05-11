package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
class NoteResourceTest {

    @Test
    void testCreateAndListNotes() {
        // POST a new note
        given()
                .contentType(ContentType.JSON)
                .body("{\"title\": \"Test Note\", \"content\": \"This is a test note.\"}")
                .when().post("/notes")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", is("Test Note"))
                .body("content", is("This is a test note."))
                .body("createdAt", notNullValue());

        // GET all notes
        given()
                .when().get("/notes")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));
    }
}
