package com.jesslabs.usermanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String role;
    private String phone;
}
