# Testing APIs With Spring and RESTAssured

## Introduction

This project presents a basic structure to test APIs using a Spring Boot application 
with RESTAssured.

Although this type of test can be achieved through other means (like Servlets), Spring Boot reduces `boilerplate code` - longer, repetitive pieces of code
with little or no alteration.

- POST Request API with Spring Boot:

```java

@PostMapping("/api/resource")
public String postResource(@RequestBody String requestBody) {
    return "Received: " + requestBody;
}
```

- POST Request API with Servlets:

```java

@WebServlet("/api/resource")
public class ResourceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestBody = request.getReader().readLine(); // Manual parsing
        PrintWriter out = response.getWriter();
        out.println("Received: " + requestBody); // Manual response handling
    }
}

```

Not using Spring Boot for API development can increase code complexity leading to maintainability issues.

### Steps:

1. Step 01: Create a Spring Boot Application
2. Step 02: Install the RestAssured Dependencies
3. Step 03: Create the Folder Structure
4. Step 04: Configure the application.properties Files
5. Step 05: Create the Main Spring Boot Application Class
6. Step 06: Create the Controller Class
7. Step 07: Create the Base Configuration Class for API Tests
8. Step 08: Create the GET API Test Class
9. Step 09: Create the POST API Test Class

#### Step 01: Create a Spring Boot Application

Go to Spring Initializr and select the following options:

- Project: Maven Project
- Language: Java
- Spring Boot: (Select the latest stable version)
- Project Metadata: Fill in as needed (Group: com.example, Artifact: demo)
- Dependencies - Add:
  - Spring Web (to create REST APIs)
  - Spring Data JPA (if you’re using a database)
  - H2 Database (for in-memory testing)
  - Lombok (optional)
  - Spring Boot DevTools (for development convenience)
  - Spring Boot Starter Test (for testing support)

Click on `Generate` to download the project as a ZIP file. Unzip it and open it in IntelliJ.

#### Step 02: Install the RestAssured Dependencies

In Maven Repository, find the necessary RestAssured dependencies and add them to `pom.xml`.
RestAssured is a Java library that simplifies the process of making and testing HTTP requests for REST APIs.
Not using RestAssured means relying solely on manual testing, increasing the chance system failures.

- Example of Testing API with RestAssured:

```java
package com.example.demo.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * This class tests a Spring Boot API using RestAssured for validating HTTP responses.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class GetApiTests {

    @Test
    public void testGetHelloEndpoint() {
        given()  // Setup request
                .when().get("/api/hello")  // Make GET request
                .then()
                .statusCode(200)  // Check that status is 200
                .body(equalTo("Hello, World!"));  // Check response body
    }
}

```
- Example of Testing API without RestAssured:

```java
package com.example.demo.api;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class manually tests an API without using RestAssured or Spring Boot.
 */
public class ManualApiTest {

    @Test
    public void testGetHelloEndpoint() throws Exception {
        // Manually creating the request
        URL url = new URL("http://localhost:8080/api/hello");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Get response status code
        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);

        // Get response body
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        // Check response body
        assertEquals("Hello, World!", content.toString());
    }
}

```
- **With RestAssured:** RestAssured automates the request, validation, and asserts API responses.
- **Without RestAssured:** Each request and validation must be written manually, which is cumbersome.

#### Step 03: Create the Folder Structure

APITestWithRESTAssured/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── demo/
│   │   │               ├── DemoApplication.java       # Main Spring Boot Application
│   │   │               └── controller/
│   │   │                   └── HelloController.java   # API Controller
│   │   ├── resources/
│   │   │   └── application.properties                 # Main application configuration
│   │
│   ├── test/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── demo/
│       │               └── api/
│       │                   ├── ApiTestBase.java       # Base test class for setting up RestAssured
│       │                   ├── GetApiTests.java       # Test class for GET requests
│       │                   └── PostApiTests.java      # Test class for POST requests
│       └── resources/
│           └── application.properties                 # Test environment configuration
│
├── pom.xml                                            # Maven dependencies and project info
├── mvnw                                               # Maven wrapper (Linux/Mac)
└── mvnw.cmd                                           # Maven wrapper (Windows)

#### Step 04: Configure the application.properties Files
The application properties define the configuration for the Spring Boot application, including database settings and server configurations. Incorrect configurations may lead to runtime errors or the application not starting.

#### Step 05: Create the Main Spring Boot Application Class
This class contains the main method which launches the application. 
Without it, Spring Boot can't start and the tests that rely on application context won't have an environment to run.

#### Step 06: Create the Controller Class
The controller defines the API routes that the application will expose. 
Without a proper controller, there will be no endpoints to test.

#### Step 07: Create the Base Configuration Class for API Tests
In the test folder, set up a base class for configuring RestAssured.
It ensures all tests have a common setup, reducing boilerplate.

#### Step 08: Create the GET API Test Class
Also in the test folder, create the class to test the GET API endpoint.
It will ensure that the application `returns` the expected data.

#### Step 09: Create the POST API Test Class
In the test folder,create the class to test the POST API.
If you skip testing the POST requests, you risk potential failures in production when handling client requests. For example, without validation, the API might incorrectly process or reject valid requests, leading to issues like data loss, API crashes, or a poor user experience. 