package com.jesslabs.usermanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(requiredProperties = {"name", "username", "password", "email", "role", "phone"})
public class AddUserRequestDTO {
    private String name;
    private String username;
    private String password;
    private String email;
    private String role;
    private String phone;
}
