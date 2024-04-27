package com.jesslabs.usermanager.service;

import com.jesslabs.usermanager.dto.*;
import com.jesslabs.usermanager.exception.InternalServerException;
import com.jesslabs.usermanager.exception.ResourceNotFoundException;
import lombok.NonNull;

public interface UserService {

    AddUserResponseDTO addUser(@NonNull AddUserRequestDTO userAddRequestDTO) throws InternalServerException;

    UpdateUserResponseDTO updateUser(@NonNull UpdateUserRequestDTO userUpdateRequestDTO, @NonNull String username) throws ResourceNotFoundException, InternalServerException;

    GetUsersPagedDTO getUsers(Integer pageSize, Integer pageNo, String sortBy, String name, String username, String role);

    String deleteUser(@NonNull String username) throws ResourceNotFoundException, InternalServerException;

}
