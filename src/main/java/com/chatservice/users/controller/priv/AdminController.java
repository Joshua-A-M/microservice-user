package com.chatservice.users.controller.priv;

import com.chatservice.users.DTO.UserDTO;
import com.chatservice.users.exception.PersonalIDNotFoundException;
import com.chatservice.users.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping(value = "/api/v1/private")
public class AdminController {

    private static final Logger logger = LogManager.getLogger(AdminController.class);

    private final UserService service;

    public AdminController(UserService service) {
        this.service = service;
    }


    @GetMapping(value = "/users/{personalId}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable String personalId){
            return ResponseEntity.status(HttpStatus.OK).body(service.getByPersonalId(personalId));

    }




}
