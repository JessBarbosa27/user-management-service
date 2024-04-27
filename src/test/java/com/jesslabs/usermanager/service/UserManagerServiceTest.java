package com.jesslabs.usermanager.service;


import com.jesslabs.usermanager.dto.*;
import com.jesslabs.usermanager.exception.InternalServerException;
import com.jesslabs.usermanager.exception.ResourceNotFoundException;
import com.jesslabs.usermanager.mapper.UserManagerMapper;
import com.jesslabs.usermanager.model.User;
import com.jesslabs.usermanager.repository.UserManagerRepository;
import com.jesslabs.usermanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserManagerServiceTest {

    @Mock
    private UserManagerMapper mapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserManagerRepository userManagerRepository;

    @InjectMocks
    private UserServiceImplementation userService;

    private User user;
    private AddUserRequestDTO addUserRequestDTO;
    private UpdateUserRequestDTO updateUserRequestDTO;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Jess Barbosa", "jessbarbosa", "thisismypassword", "barbosajess27@gmail.com", "Developer", "+44 7459407657");
        addUserRequestDTO = new AddUserRequestDTO("Jess Barbosa", "jessbarbosa", "thisismypassword", "barbosajess27@gmail.com", "Developer", "+44 7459407657");
        updateUserRequestDTO = new UpdateUserRequestDTO(1L, "Jess Barbosa", "jessbarbosa", "thisismyupdatedpassword", "barbosajess27@gmail.com", "Fullstack Developer", "+44 7459407657");
    }

    @Test
    void testAddUser() {
        when(mapper.addUserRequestDTOToUser(any(AddUserRequestDTO.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        AddUserResponseDTO result = userService.addUser(addUserRequestDTO);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getRole(), result.getRole());
        assertEquals(user.getPhone(), result.getPhone());
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UpdateUserResponseDTO result = userService.updateUser(updateUserRequestDTO);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(updateUserRequestDTO.getName(), result.getName());
        assertEquals(updateUserRequestDTO.getUsername(), result.getUsername());
        assertEquals(updateUserRequestDTO.getEmail(), result.getEmail());
        assertEquals(updateUserRequestDTO.getRole(), result.getRole());
        assertEquals(updateUserRequestDTO.getPhone(), result.getPhone());
    }

    @Test
    void testUpdateUser_NotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUser(updateUserRequestDTO);
        });

        assertTrue(exception.getMessage().contains("Could not find user with id"));
    }

    @Test
    void testAddUser_ThrowsException() {
        when(mapper.addUserRequestDTOToUser(any(AddUserRequestDTO.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("error"));

        assertThrows(InternalServerException.class, () -> {
            userService.addUser(addUserRequestDTO);
        });
    }

    @Test
    void testUpdateUser_ThrowsException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Database error"));

        assertThrows(InternalServerException.class, () -> {
            userService.updateUser(updateUserRequestDTO);
        });
    }

//    @Test
//    void testGetUsers() {
//        GetUsersDTO getUsersDTO = new GetUsersDTO(1L, "Jess Barbosa", "jessbarbosa", "barbosajess27@gmail.com", "Developer", "+44 7459407657");
//
//        Page<User> users = new PageImpl<>(Collections.singletonList(user), PageRequest.of(0, 10, Sort.by("name")), 1);
//
//        when(userManagerRepository.getUsers(any(), anyString(), anyString(), anyString())).thenReturn(users);
//
//        GetUsersPagedDTO getUsersPagedDTO = userService.getUsers(10, 0, "id", null, null, null);
//
//        assertNotNull(getUsersPagedDTO);
//        assertEquals(1, getUsersPagedDTO.getPage().getTotalPages());
//        assertEquals(1, getUsersPagedDTO.getUsers().size());
//    }

    @Test
    void testGetUsers_EmptyResult() {
        Page<User> users = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10, Sort.by("name")), 0);
        when(userManagerRepository.getUsers(any(), anyString(), anyString(), anyString())).thenReturn(null);

        GetUsersPagedDTO result = userService.getUsers(10, 0, "name", "", "", "");

        assertNotNull(result);
        assertEquals(0, result.getPage().getTotalPages());
        assertTrue(result.getUsers().isEmpty());
    }
}
}
