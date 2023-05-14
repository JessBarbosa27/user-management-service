package com.jesslabs.usermanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author barbo on 23-03-2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserResponseDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String role;
    private String phone;
}
