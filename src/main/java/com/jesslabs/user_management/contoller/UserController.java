package com.jesslabs.user_management.contoller;

import com.jesslabs.user_management.dto.*;
import com.jesslabs.user_management.exception.InternalServerException;
import com.jesslabs.user_management.exception.ResourceNotFoundException;
import com.jesslabs.user_management.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user-management/v1/")
@CrossOrigin
@OpenAPIDefinition(info = @Info(title = "User controller"), tags = @Tag(name = "User Controller"))
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "user")
    @Operation(description = "Creates a new user")
    public ResponseEntity<AddUserResponseDTO> addUser(@Valid @RequestBody AddUserRequestDTO addUserRequestDTO) throws InternalServerException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(addUserRequestDTO));
    }

    @PatchMapping(value = "user/{username}")
    @Operation(description = "Updates user by username")
    public ResponseEntity<UpdateUserResponseDTO> updateUser(@RequestBody UpdateUserRequestDTO updateUserRequestDTO, @PathVariable String username) throws ResourceNotFoundException, InternalServerException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(updateUserRequestDTO, username));
    }

    @DeleteMapping(value = "user/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) throws ResourceNotFoundException, InternalServerException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(username));
    }

    @GetMapping(value = "users")
    public ResponseEntity<GetUsersPagedDTO> getUsers(@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(name = "name", defaultValue = "") String name, @RequestParam(name = "username", defaultValue = "") String username, @RequestParam(name = "role", defaultValue = "") String role) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers(pageSize, pageNo, sortBy, name, username, role));
    }
}
