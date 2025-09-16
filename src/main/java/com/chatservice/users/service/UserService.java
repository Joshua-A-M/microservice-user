package com.chatservice.users.service;

import com.chatservice.users.DTO.UserDTO;
import com.chatservice.users.entity.BasicUserEntity;

public interface UserService {

    public UserDTO create(UserDTO dto);
    public UserDTO getByPersonalId(String personalId);
    public boolean changeUsername(String username, String newUsername);

    public boolean deleteUserByPersonalId(String personalId);
}
