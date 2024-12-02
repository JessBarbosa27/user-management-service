package com.jesslabs.user_management.service;


import com.jesslabs.user_management.dto.AddUserRequestDTO;
import com.jesslabs.user_management.dto.AddUserResponseDTO;
import com.jesslabs.user_management.dto.UpdateUserRequestDTO;
import com.jesslabs.user_management.dto.UpdateUserResponseDTO;
import com.jesslabs.user_management.exception.InternalServerException;
import com.jesslabs.user_management.exception.ResourceNotFoundException;
import com.jesslabs.user_management.mapper.UserManagementMapper;
import com.jesslabs.user_management.model.User;
import com.jesslabs.user_management.repository.UserManagementRepository;
import com.jesslabs.user_management.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserManagerServiceTest {

    @Mock
    private UserManagementMapper mapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserManagementRepository userManagementRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserServiceImplementation userService;

    private User user;
    private AddUserRequestDTO addUserRequestDTO;
    private UpdateUserRequestDTO updateUserRequestDTO;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Jess Barbosa", "jessbarbosa", "thisismypassword", "barbosajess27@gmail.com", "Developer", "+44 7459407657", false);
        addUserRequestDTO = new AddUserRequestDTO("Jess Barbosa", "jessbarbosa", "thisismypassword", "barbosajess27@gmail.com", "Developer", "+44 7459407657");
        updateUserRequestDTO = new UpdateUserRequestDTO("Jess Barbosa", "thisismyupdatedpassword", "barbosajess27@gmail.com", "Fullstack Developer", "+44 7459407657");
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
        when(userRepository.findUserByUsername("jessbarbosa")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UpdateUserResponseDTO result = userService.updateUser(updateUserRequestDTO, "jessbarbosa");

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(updateUserRequestDTO.getName(), result.getName());
        assertEquals("jessbarbosa", result.getUsername());
        assertEquals(updateUserRequestDTO.getEmail(), result.getEmail());
        assertEquals("Developer", result.getRole());
        assertEquals(updateUserRequestDTO.getPhone(), result.getPhone());
    }

    @Test
    void testUpdateUser_ThrowsException() {
        when(userRepository.findUserByUsername("jessbarbosa")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenThrow(new InternalServerException("error"));

        assertThrows(InternalServerException.class, () -> {
            userService.updateUser(updateUserRequestDTO, "jessbarbosa");
        });
    }

    @Test
    void testDeleteUser_Success() throws Exception {
        when(userRepository.findUserByUsername("jessbarbosa")).thenReturn(Optional.of(user));

        String expectedMessage = "Successfully deleted user with username: jessbarbosa";
        String resultMessage = userService.deleteUser("jessbarbosa");

        verify(userRepository).save(user);
        assertTrue(user.isDiscarded());
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        when(userRepository.findUserByUsername("unknownuser")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteUser("unknownuser");
        });
    }

    @Test
    void testDeleteUser_InternalServerError() {
        when(userRepository.findUserByUsername("jessbarbosa")).thenReturn(Optional.of(user));
        doThrow(RuntimeException.class).when(userRepository).save(any(User.class));

        assertThrows(InternalServerException.class, () -> {
            userService.deleteUser("jessbarbosa");
        });
    }

}

