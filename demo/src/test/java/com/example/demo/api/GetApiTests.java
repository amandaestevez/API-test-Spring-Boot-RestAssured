package com.example.demo.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Test class to test GET requests on API endpoints.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test") // Use the test profile
public class GetApiTests extends ApiTestBase {

    /**
     * Test the /api/hello GET endpoint.
     */
    @Test
    public void testGetHelloEndpoint() {
        given()
                .when().get("/api/hello")
                .then().statusCode(200)
                .body(equalTo("Hello, World!")); // Adjusted to match the response from your controller
    }
}
