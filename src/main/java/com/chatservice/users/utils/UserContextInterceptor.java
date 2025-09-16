package com.chatservice.users.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    private final static Logger logger = LogManager.getLogger(UserContextInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());

        logger.info("\nUserContextInterceptor CorrelationId: {}", UserContextHolder.getContext().getCorrelationId());

        return execution.execute(request, body);
    }
}
