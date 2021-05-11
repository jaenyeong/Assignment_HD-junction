package com.hdjunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HdJunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdJunctionApplication.class, args);
    }

}
