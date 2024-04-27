package com.jesslabs.usermanager.service;

import com.jesslabs.usermanager.dto.*;
import com.jesslabs.usermanager.exception.InternalServerException;
import com.jesslabs.usermanager.exception.ResourceNotFoundException;
import com.jesslabs.usermanager.mapper.UserManagerMapper;
import com.jesslabs.usermanager.model.User;
import com.jesslabs.usermanager.repository.UserManagerRepository;
import com.jesslabs.usermanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImplementation implements UserService {

    private final UserManagerMapper mapper;

    private final UserRepository userRepository;

    private final UserManagerRepository userManagerRepository;

    public UserServiceImplementation(UserManagerMapper mapper, UserRepository userRepository, UserManagerRepository userManagerRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.userManagerRepository = userManagerRepository;
    }

    @Override
    public AddUserResponseDTO addUser(AddUserRequestDTO userAddRequestDTO) throws InternalServerException {
        log.info("creating user with username: {}", userAddRequestDTO.getUsername());
        User user = mapper.addUserRequestDTOToUser(userAddRequestDTO);
        try {
            userRepository.save(user);
            log.info("successfully created user with username: {}", userAddRequestDTO.getUsername());

            return new AddUserResponseDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getRole(), user.getPhone());
        } catch (Exception e) {
            throw new InternalServerException("Unexpected error occurred while creating new user.");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO userUpdateRequestDTO) throws ResourceNotFoundException, InternalServerException {
        log.info("updating user with id: {}", userUpdateRequestDTO.getId());
        User user = userRepository.findById(userUpdateRequestDTO.getId())
                .orElseThrow(() -> {
                    String errorMessage = String.format("Could not find user with id %s", userUpdateRequestDTO.getId());
                    log.warn(errorMessage);
                    return new ResourceNotFoundException(errorMessage);
                });

        try {
            mapper.updateUserRequestDTOToUser(userUpdateRequestDTO, user);
            userRepository.save(user);
            log.info("successfully updated user with id: {}", userUpdateRequestDTO.getId());

            return new UpdateUserResponseDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getRole(), user.getPhone());
        } catch (Exception e) {
            log.error("failed to update user", e);
            throw new InternalServerException("Failed to update user due to an unexpected error.");
        }
    }

    @Override
    public GetUsersPagedDTO getUsers(Integer pageSize, Integer pageNo, String sortBy, String name, String username, String role) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<GetUsersDTO> pageResult = this.userManagerRepository.getUsers(paging, name, username, role);
        return new GetUsersPagedDTO(pageResult.getContent(), new PageDTO(pageResult.getTotalPages(),
                pageResult.getNumber(), pageResult.getNumberOfElements(), pageResult.getTotalElements()));
    }
}
