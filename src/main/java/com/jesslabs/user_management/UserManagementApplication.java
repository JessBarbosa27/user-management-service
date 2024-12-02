package com.jesslabs.user_management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@OpenAPIDefinition(info = @Info(title = "User management service", version = "1.0",
        description = "User management service",
        contact = @Contact(name = "Jess Barbosa", email = "barbosajess27@gmail.com", url = "https://www.linkedin.com/in/jess-barbosa/"))
)
@SpringBootApplication
@EnableTransactionManagement
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

}
