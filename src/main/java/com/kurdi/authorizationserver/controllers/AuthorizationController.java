package com.kurdi.authorizationserver.controllers;

import com.kurdi.authorizationserver.entities.Authority;
import com.kurdi.authorizationserver.entities.IdentityUser;
import com.kurdi.authorizationserver.repositories.AuthoritiesRepository;
import com.kurdi.authorizationserver.requests.UserNameAndPasswordAuthenticationRequest;
import com.kurdi.authorizationserver.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class AuthorizationController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

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



    @PostMapping("/register")
    public ResponseEntity<IdentityUser> register(@RequestBody UserNameAndPasswordAuthenticationRequest authenticationRequest)
    {

        IdentityUser user = authService.register(authenticationRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

/*    @PostMapping
    public ResponseEntity<IdentityUser> addAuthorities(Integer userId, Set<Authority> authorities)
    {

    }*/
    @GetMapping("/")
    public ResponseEntity<List<Authority>> authorities()
    {
        return new ResponseEntity<>(authoritiesRepository.findAll(), HttpStatus.OK);
    }
}
