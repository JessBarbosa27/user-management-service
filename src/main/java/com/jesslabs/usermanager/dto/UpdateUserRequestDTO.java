package com.jesslabs.usermanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(requiredProperties = {"id"})
public class UpdateUserRequestDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String role;
    private String phone;
}
