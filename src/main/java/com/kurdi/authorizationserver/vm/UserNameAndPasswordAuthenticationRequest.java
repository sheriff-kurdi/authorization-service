package com.kurdi.authorizationserver.vm;

import lombok.Data;

@Data
public class UserNameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
