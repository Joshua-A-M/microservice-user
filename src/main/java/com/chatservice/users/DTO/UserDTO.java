package com.chatservice.users.DTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserDTO {


    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;

    private String personalId;

    private String password;
}
