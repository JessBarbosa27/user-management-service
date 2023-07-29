package com.jesslabs.usermanager.service;

import com.jesslabs.usermanager.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    AddUserResponseDTO addUser(AddUserRequestDTO userAddRequestDTO) throws Exception;


    UpdateUserResponseDTO updateUser(UpdateUserRequestDTO userUpdateRequestDTO) throws Exception;

    GetUsersPagedDTO getUsers(Integer pageSize, Integer pageNo, String sortBy, String name, String username, String role);
}
