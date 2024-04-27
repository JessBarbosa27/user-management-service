package com.jesslabs.usermanager.service;

import com.jesslabs.usermanager.dto.*;
import com.jesslabs.usermanager.exception.ConflictException;
import com.jesslabs.usermanager.exception.InternalServerException;
import com.jesslabs.usermanager.exception.ResourceNotFoundException;
import com.jesslabs.usermanager.mapper.UserManagerMapper;
import com.jesslabs.usermanager.model.User;
import com.jesslabs.usermanager.repository.UserManagerRepository;
import com.jesslabs.usermanager.repository.UserRepository;
import lombok.NonNull;
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
    @Transactional(rollbackFor = Exception.class)
    public AddUserResponseDTO addUser(@NonNull AddUserRequestDTO userAddRequestDTO) throws InternalServerException {
        log.info("creating user with username: {}", userAddRequestDTO.getUsername());

        try {
            if (userRepository.existsUserByUsername(userAddRequestDTO.getUsername())) {
                String errorMessage = String.format("user with username: %s already exist, please enter a unique username.", userAddRequestDTO.getUsername());
                log.warn(errorMessage);
                throw new ConflictException(errorMessage);
            }

            User user = mapper.addUserRequestDTOToUser(userAddRequestDTO);

            userRepository.save(user);
            log.info("successfully created user with username: {}", userAddRequestDTO.getUsername());
            return new AddUserResponseDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getRole(), user.getPhone());
        } catch (Exception e) {
            String errorMessage = String.format("error occurred while creating new user with username: %s", userAddRequestDTO.getUsername());
            log.warn(errorMessage);
            throw new InternalServerException(errorMessage);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UpdateUserResponseDTO updateUser(@NonNull UpdateUserRequestDTO userUpdateRequestDTO, @NonNull String username) throws ResourceNotFoundException, InternalServerException {
        log.info("updating user with username: {}", username);
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> {
                    String errorMessage = String.format("Could not find user with username: %s", username);
                    log.warn(errorMessage);
                    return new ResourceNotFoundException(errorMessage);
                });

        try {
            mapper.updateUserRequestDTOToUser(userUpdateRequestDTO, user);
            userRepository.save(user);
            log.info("successfully updated user with username: {}", username);

            return new UpdateUserResponseDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getRole(), user.getPhone());
        } catch (Exception e) {
            String errorMessage = String.format("error occurred while updating user with username: %s", username);
            log.error(errorMessage, e);
            throw new InternalServerException(errorMessage);
        }
    }

    @Override
    public GetUsersPagedDTO getUsers(Integer pageSize, Integer pageNo, String sortBy, String name, String username, String role) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<GetUsersDTO> pageResult = userManagerRepository.getUsers(paging, name, username, role);
        return new GetUsersPagedDTO(pageResult.getContent(), new PageDTO(pageResult.getTotalPages(),
                pageResult.getNumber(), pageResult.getNumberOfElements(), pageResult.getTotalElements()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteUser(@NonNull String username) throws ResourceNotFoundException, InternalServerException {
        log.info("deleting user with username: {}", username);

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> {
                    String errorMessage = String.format("Could not find user with username: %s", username);
                    log.warn(errorMessage);
                    return new ResourceNotFoundException(errorMessage);
                });
        try {
            user.setDiscarded(true);
            userRepository.save(user);

            String successMessage = String.format("Successfully deleted user with username: %s", username);
            log.info(successMessage);
            return successMessage;
        } catch (Exception e) {
            String errorMessage = String.format("error occurred while deleting user with username: %s", username);
            log.error(errorMessage, e);
            throw new InternalServerException(errorMessage);
        }
    }
}
