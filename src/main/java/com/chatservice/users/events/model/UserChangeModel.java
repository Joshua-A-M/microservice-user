package com.chatservice.users.events.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @Getter @Setter @ToString
public class UserChangeModel {

    private String type;
    private String action;
    private String personalId;
    private String correlationId;

}
