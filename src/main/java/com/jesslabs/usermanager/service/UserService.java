package com.jesslabs.usermanager.service;

import com.jesslabs.usermanager.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * @param userAddRequestDTO
     * @return
     * @throws Exception
     */
    AddUserResponseDTO addUser(AddUserRequestDTO userAddRequestDTO) throws Exception;


    /**
     * @param userUpdateRequestDTO
     * @return
     * @throws Exception
     */
    UpdateUserResponseDTO updateUser(UpdateUserRequestDTO userUpdateRequestDTO) throws Exception;

    /**
     * @param pageSize
     * @param pageNo
     * @param sortBy
     * @param name
     * @param username
     * @param role
     * @return
     */
    GetUsersPagedDTO getUsers(Integer pageSize, Integer pageNo, String sortBy, String name, String username, String role);
}
