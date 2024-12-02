package com.jesslabs.user_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String role;
    private String phone;
}
