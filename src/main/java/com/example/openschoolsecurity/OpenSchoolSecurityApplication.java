package com.example.openschoolsecurity;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class OpenSchoolSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenSchoolSecurityApplication.class, args);
    }

}
