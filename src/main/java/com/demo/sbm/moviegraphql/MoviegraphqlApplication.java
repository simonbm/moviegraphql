package com.demo.sbm.moviegraphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class MoviegraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviegraphqlApplication.class, args);
    }
}
