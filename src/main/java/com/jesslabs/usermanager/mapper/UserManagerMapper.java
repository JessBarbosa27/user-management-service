package com.jesslabs.usermanager.mapper;

import com.jesslabs.usermanager.dto.AddUserRequestDTO;
import com.jesslabs.usermanager.dto.UpdateUserRequestDTO;
import com.jesslabs.usermanager.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author barbo on 23-03-2023
 */
@Repository
public class UserManagerMapper {

    public User mapToUser(AddUserRequestDTO userAddRequestDTO) {
        return new User(null, userAddRequestDTO.getName(), userAddRequestDTO.getUsername(),userAddRequestDTO.getPassword(), userAddRequestDTO.getEmail(),
                userAddRequestDTO.getRole(), userAddRequestDTO.getPhone());
    }

    public User mapToUser(UpdateUserRequestDTO userUpdateRequestDTO) {
        return new User(userUpdateRequestDTO.getId(), userUpdateRequestDTO.getName(), userUpdateRequestDTO.getUsername(), userUpdateRequestDTO.getPassword(),
                userUpdateRequestDTO.getEmail(), userUpdateRequestDTO.getRole(), userUpdateRequestDTO.getPhone());
    }
}
