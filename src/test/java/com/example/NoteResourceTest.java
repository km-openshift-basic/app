package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;

@QuarkusTest
class NoteResourceTest {

    @Test
    void testCreateAndListNotes() {
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

        given()
                .when().get("/notes")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    void testCreateNoteWithLongContent() {
        String longContent = "A".repeat(2000);
        given()
                .contentType(ContentType.JSON)
                .body("{\"title\": \"Long Note\", \"content\": \"" + longContent + "\"}")
                .when().post("/notes")
                .then()
                .statusCode(201)
                .body("title", is("Long Note"));
    }

    @Test
    void testCreateNoteWithoutContent() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"title\": \"Title Only\"}")
                .when().post("/notes")
                .then()
                .statusCode(201)
                .body("title", is("Title Only"));
    }

    @Test
    void testCreateMultipleNotes() {
        for (int i = 1; i <= 3; i++) {
            given()
                    .contentType(ContentType.JSON)
                    .body("{\"title\": \"Bulk Note " + i + "\", \"content\": \"Content " + i + "\"}")
                    .when().post("/notes")
                    .then()
                    .statusCode(201);
        }

        given()
                .when().get("/notes")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(3));
    }

    @Test
    void testNoteTimestampIsSet() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"title\": \"Timestamp Check\", \"content\": \"Verify timestamp.\"}")
                .when().post("/notes")
                .then()
                .statusCode(201)
                .body("createdAt", notNullValue())
                .body("createdAt", not(emptyString()));
    }

    @Test
    void testListNotesReturnsJson() {
        given()
                .accept(ContentType.JSON)
                .when().get("/notes")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
}
