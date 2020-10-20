package com.gpro.consulting.tiers.commun.rest.security;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {


    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}