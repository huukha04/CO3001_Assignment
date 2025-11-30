package com.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        System.out.println("Starting Backend Application...");
        SpringApplication.run(BackendApplication.class, args);
        System.out.println("Backend Application Started Successfully.");
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            mongoTemplate.getDb().listCollectionNames().first();
            System.out.println("[DEBUG: SUCCESS] MongoDB Connected Successfully!");
        } catch (Exception e) {
            System.out.println("[DEBUG: ERROR] MongoDB Connection Failed: " + e.getMessage());
        }
    }
}
