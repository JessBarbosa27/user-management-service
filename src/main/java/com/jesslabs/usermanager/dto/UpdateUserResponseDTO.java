package com.jesslabs.usermanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author barbo on 22-03-2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponseDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String role;
    private String phone;
}
