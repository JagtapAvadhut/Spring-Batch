package com.spring_batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchApplication {
    //http://localhost:8080/jobs/importData this GET call
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }

}
