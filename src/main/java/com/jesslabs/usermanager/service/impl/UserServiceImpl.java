package com.jesslabs.usermanager.service.impl;

import com.jesslabs.usermanager.dto.*;
import com.jesslabs.usermanager.mapper.UserManagerMapper;
import com.jesslabs.usermanager.model.User;
import com.jesslabs.usermanager.repository.IUserRepository;
import com.jesslabs.usermanager.repository.UserManagerRepository;
import com.jesslabs.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * @author barbo on 23-03-2023
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserManagerMapper mapper;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserManagerRepository userManagerRepository;

    @Override
    public AddUserResponseDTO addUser(AddUserRequestDTO userAddRequestDTO) throws Exception {
        User user = mapper.mapToUser(userAddRequestDTO);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("Sorry for the inconvenience, Failed to Add User due to unexpected error, please try again.");
        }
        return new AddUserResponseDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getRole(), user.getPhone());
    }

    @Override
    public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO userUpdateRequestDTO) throws Exception {
        User user = null;
        try {
            user = this.userRepository.findById(userUpdateRequestDTO.getId()).get();
        } catch (Exception e) {
            throw new Exception("User Not Found!!");
        }
        if (user != null) {
            user = mapper.mapToUser(userUpdateRequestDTO);
            try {
                userRepository.save(user);
            } catch (Exception e) {
                throw new Exception("Sorry for the inconvenience, Failed to Update User due to unexpected error, please try again.");
            }
        }
        return new UpdateUserResponseDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getRole(), user.getPhone());
    }

    @Override
    public GetUsersPagedDTO getUsers(Integer pageSize, Integer pageNo, String sortBy, String name, String username, String role) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<GetUsersDTO> pageResult = this.userManagerRepository.getUsers(paging, name, username, role);
        return new GetUsersPagedDTO(pageResult.getContent(), new PageDTO(pageResult.getTotalPages(),
                pageResult.getNumber(), pageResult.getNumberOfElements(), pageResult.getTotalElements()));
    }
}
