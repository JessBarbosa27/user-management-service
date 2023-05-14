package com.jesslabs.usermanager.contoller;

import com.jesslabs.usermanager.dto.*;
import com.jesslabs.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author barbo on 23-03-2023
 */

@RestController
@RequestMapping(value = "/um")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/add/user")
    public ResponseEntity<AddUserResponseDTO> addUser(@RequestHeader HttpHeaders headers,
                                                      @RequestBody AddUserRequestDTO request) throws Exception {
        AddUserResponseDTO response = null;
        try {
            response = this.userService.addUser(request);
            return new ResponseEntity<AddUserResponseDTO>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    @PutMapping(value = "/update/user")
    public ResponseEntity<UpdateUserResponseDTO> updateUser(@RequestHeader HttpHeaders headers,
                                                      @RequestBody UpdateUserRequestDTO request) throws Exception {
        UpdateUserResponseDTO response = null;
        try {
            response = this.userService.updateUser(request);
            return new ResponseEntity<UpdateUserResponseDTO>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/get/users")
    public ResponseEntity<GetUsersPagedDTO> getUsers(@RequestHeader HttpHeaders headers,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(name = "name", defaultValue = "") String name,
                                                     @RequestParam(name = "username", defaultValue = "") String username,
                                                     @RequestParam(name = "role", defaultValue = "") String role) throws Exception {
        GetUsersPagedDTO response = null;
        try {
            response = this.userService.getUsers(pageSize, pageNo, sortBy, name, username, role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<GetUsersPagedDTO>(response, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
