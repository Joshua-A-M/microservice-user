package com.chatservice.users.entity.utility;

import java.util.Random;
import java.util.UUID;

public class GenerateValues {

    public String generateUsername(String firstname, String lastname){
        StringBuilder username = new StringBuilder();

        username.append(Character.toUpperCase(firstname.charAt(0)));
        username.append(Character.toUpperCase(lastname.charAt(0)));
        username.append(lastname.substring(1));

        String randomN = String.format("%04d",new Random().nextInt(10000));
        username.append(randomN);
        return username.toString();
    }

    public static String generatePersonalId(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
