package com.jesslabs.user_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(requiredProperties = {"name", "username", "password", "email", "role", "phone"})
public class AddUserRequestDTO {

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "role is required")
    private String role;

    @NotNull(message = "phone is required")
    private String phone;

}
