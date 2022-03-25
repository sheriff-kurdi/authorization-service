package com.kurdi.authorizationserver.requests;

import lombok.Data;

@Data
public class UserNameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
