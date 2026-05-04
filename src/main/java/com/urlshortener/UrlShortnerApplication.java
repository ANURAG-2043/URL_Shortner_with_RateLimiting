package com.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Main class of the Spring Boot Application
 *
 * @SpringBootApplication includes:
 * 1. @Configuration
 * 2. @EnableAutoConfiguration
 * 3. @ComponentScan
 *
 * This starts the embedded Tomcat server
 */

@SpringBootApplication
public class UrlShortnerApplication {

    public static void main(String[] args) {

        // Launch Spring Boot application
        SpringApplication.run(UrlShortnerApplication.class, args);

        System.out.println("URL Shortener Application Started 🚀");
    }
}