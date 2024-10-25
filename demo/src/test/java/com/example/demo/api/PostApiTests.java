package com.example.demo.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Test class to verify the behavior of POST API endpoints in the application.
 * <p>
 * This class contains tests for the {@code /api/resource} POST endpoint using RestAssured
 * and verifies the correct HTTP response and content handling. The test base class
 * {@link ApiTestBase} sets up the RestAssured configuration.
 * </p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class PostApiTests extends ApiTestBase {

    /**
     * Tests the POST endpoint {@code /api/resource}.
     * <p>
     * This method sends a POST request with a sample request body and asserts that
     * the response status code is 200 and the response body matches the expected value.
     * </p>
     */
    @Test
    public void testPostEndpoint() {
        // Sample request body for the POST request
        String requestBody = "Sample POST Request";

        // Perform the POST request and validate the response
        given()
                .contentType("text/plain")  // Set the content type to plain text
                .body(requestBody)          // Add the request body
                .when()
                .post("/api/resource")      // Make the POST request to /api/resource
                .then()
                .statusCode(200)            // Assert that the status code is 200 OK
                .body(equalTo("Received: " + requestBody)); // Assert that the response body is as expected
    }
}
