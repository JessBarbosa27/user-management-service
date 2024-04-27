package com.jesslabs.user_management.service;

import com.jesslabs.user_management.dto.*;
import com.jesslabs.user_management.exception.InternalServerException;
import com.jesslabs.user_management.exception.ResourceNotFoundException;
import lombok.NonNull;

public interface UserService {

    AddUserResponseDTO addUser(@NonNull AddUserRequestDTO userAddRequestDTO) throws InternalServerException;

    UpdateUserResponseDTO updateUser(@NonNull UpdateUserRequestDTO userUpdateRequestDTO, @NonNull String username) throws ResourceNotFoundException, InternalServerException;

    GetUsersPagedDTO getUsers(Integer pageSize, Integer pageNo, String sortBy, String name, String username, String role);

    String deleteUser(@NonNull String username) throws ResourceNotFoundException, InternalServerException;

}
