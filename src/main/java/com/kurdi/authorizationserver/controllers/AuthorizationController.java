package com.kurdi.authorizationserver.controllers;

import com.kurdi.authorizationserver.requests.UserNameAndPasswordAuthenticationRequest;
import com.kurdi.authorizationserver.services.UsersAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {
    @Autowired
    UsersAuthService usersAuthService;
    @GetMapping("/")
    public String index()
    {
        return "hello";
    }
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/admin")
    public String admin()
    {
        return "hello admin";
    }

    @PostMapping("/login")
    public String login(UserNameAndPasswordAuthenticationRequest authenticationRequest)
    {
        return "Ok";
    }

    @PostMapping("/register")
    public String register( @RequestBody UserNameAndPasswordAuthenticationRequest authenticationRequest)
    {
        usersAuthService.register(authenticationRequest);
        //return  token
        return "Ok";
    }
}
