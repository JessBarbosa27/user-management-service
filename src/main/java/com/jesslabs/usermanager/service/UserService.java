package com.jesslabs.usermanager.service;

import com.jesslabs.usermanager.dto.*;
import com.jesslabs.usermanager.exception.InternalServerException;
import com.jesslabs.usermanager.exception.ResourceNotFoundException;

public interface UserService {

    AddUserResponseDTO addUser(AddUserRequestDTO userAddRequestDTO) throws InternalServerException;

    UpdateUserResponseDTO updateUser(UpdateUserRequestDTO userUpdateRequestDTO) throws ResourceNotFoundException, InternalServerException;

    GetUsersPagedDTO getUsers(Integer pageSize, Integer pageNo, String sortBy, String name, String username, String role);
}
