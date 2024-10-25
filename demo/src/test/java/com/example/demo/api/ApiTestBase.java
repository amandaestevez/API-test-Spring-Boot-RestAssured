package com.example.demo.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

/**
 * Base test class for setting up RestAssured configurations.
 */
public abstract class ApiTestBase {

	/**
	 * Set up the base URI for Rest Assured before each test.
	 */
	@BeforeEach
	public void setup() {
		RestAssured.baseURI = "http://localhost:8080"; // Ensure this matches your application port
	}
}
