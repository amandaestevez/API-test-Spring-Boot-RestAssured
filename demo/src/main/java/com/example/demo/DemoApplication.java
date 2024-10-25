package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Spring Boot application.
 * <p>
 * This class contains the main method which launches the application.
 * It uses {@link SpringApplication#run(Class, String...)} to bootstrap and start the Spring Boot application.
 * </p>
 */
@SpringBootApplication
public class DemoApplication {

	/**
	 * The main method which serves as the entry point for the Spring Boot application.
	 *
	 * @param args Command-line arguments passed during application startup.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args); // Starts the Spring Boot application
	}
}
