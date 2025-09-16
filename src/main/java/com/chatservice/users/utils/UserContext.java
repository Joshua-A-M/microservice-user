package com.chatservice.users.utils;

import org.springframework.stereotype.Component;


@Component
public class UserContext {


    public static final String CORRELATION_ID = "tmx-correlation-id";

    public static final String EMPLOYEE_PID = "tmx-employee-id";

    public static final String USER_PID = "tmx-user-id";


    private static final ThreadLocal<String> correlationId = new ThreadLocal<>();
    private static final ThreadLocal<String> employeePid = new ThreadLocal<>();
    private static final ThreadLocal<String> userPid = new ThreadLocal<>();

    public static String getCorrelationId(){
        return correlationId.get();
    }

    public static String getEmployeePid(){
        return employeePid.get();
    }

    public static String getUserPid(){
        return userPid.get();
    }

    public void setCorrelationId(String cid){
        correlationId.set(cid);
    }

    public void setEmployeePid(String epid){
        employeePid.set(epid);
    }

    public void setUserPid(String upid){
        userPid.set(upid);
    }

}
