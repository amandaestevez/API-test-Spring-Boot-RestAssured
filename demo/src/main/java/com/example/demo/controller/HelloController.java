package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple REST controller to demonstrate GET and POST endpoints.
 * This controller returns greetings based on the request method.7
 * Serves as the API I'll write the tests for.
 */
@RestController
public class HelloController {

    /**
     * Handles GET requests to "/api/hello".
     *
     * @return A simple "Hello, World!" message as the response body.
     */
    @GetMapping("/api/hello")
    public String getHello() {
        return "Hello, World!";
    }

    /**
     * Handles POST requests to "/api/hello".
     *
     * @param name The name sent in the request body.
     * @return A greeting message that includes the provided name.
     */
    @PostMapping("/api/hello")
    public String postHello(@RequestBody String name) {
        return "Hello, " + name + "!";
    }

    @PostMapping("/api/resource")  // Add this method
    public String postResource(@RequestBody String requestBody) {
        return "Received: " + requestBody;
    }
}
