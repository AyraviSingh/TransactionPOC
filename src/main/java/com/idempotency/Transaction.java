package com.idempotency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * This is the main application.
 */
@SpringBootApplication
@EnableMongoAuditing
public class Transaction {
    public static void main(String[] args) {
        SpringApplication.run(Transaction.class, args);
    }
}