package com.jesslabs.user_management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@OpenAPIDefinition(info = @Info(title = "User management service", version = "1.0",
        description = "User management service",
        contact = @Contact(name = "Jess Barbosa")),
        security = {@SecurityRequirement(name = "bearerToken")}
)
@SpringBootApplication
@EnableTransactionManagement
public class UserManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagerApplication.class, args);
    }

}
