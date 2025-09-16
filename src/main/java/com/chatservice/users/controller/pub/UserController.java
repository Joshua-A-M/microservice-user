package com.chatservice.users.controller.pub;

import com.chatservice.users.DTO.UserDTO;
import com.chatservice.users.exception.CannotCreateResourceException;
import com.chatservice.users.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping(value = "/api/v1/public")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/users/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PatchMapping(value = "/users/username")
    public ResponseEntity<?> updateUsername(@RequestParam String username, @RequestParam String newUsername){
        if(service.changeUsername(username, newUsername)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    @DeleteMapping(value = "/users/deleteUser")
    public ResponseEntity<?> deleteUserByPersonalId(String personalId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteUserByPersonalId(personalId));
    }
}
