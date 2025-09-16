package com.chatservice.users.entity;

import com.chatservice.users.entity.utility.GenerateValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "USER_ID")
public class BasicUserEntity extends UserEntity{

    @Column(unique = true)
    private String username;

    @Size(max = 10, message = "Maximum of 10 digits")
    @Column(unique = true)
    private String personalId;
    private String password;

    public BasicUserEntity(String firstname, String lastname,String email,String username, String password){
        super(firstname, lastname, email);
        this.username = username;
        this.password = password;
    }

    @PrePersist
    public void generatePersonalId(){
        personalId = GenerateValues.generatePersonalId();
    }



}
