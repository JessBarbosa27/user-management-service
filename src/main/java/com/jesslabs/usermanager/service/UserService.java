package com.jesslabs.usermanager.service;

import com.jesslabs.usermanager.dto.*;
import org.springframework.stereotype.Service;

/**
 * @author barbo on 23-03-2023
 */

@Service
public interface UserService {

    /**
     * @param userAddRequestDTO
     * @return
     * @throws Exception
     */
    public AddUserResponseDTO addUser(AddUserRequestDTO userAddRequestDTO) throws Exception;


    /**
     * @param userUpdateRequestDTO
     * @return
     * @throws Exception
     */
    public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO userUpdateRequestDTO) throws Exception;

    /**
     * @param pageSize
     * @param pageNo
     * @param sortBy
     * @param name
     * @param username
     * @param role
     * @return
     */
    public GetUsersPagedDTO getUsers(Integer pageSize, Integer pageNo, String sortBy, String name, String username, String role);
}
