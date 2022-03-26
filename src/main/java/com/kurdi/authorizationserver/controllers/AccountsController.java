package com.kurdi.authorizationserver.controllers;

import com.kurdi.authorizationserver.entities.IdentityUser;
import com.kurdi.authorizationserver.requests.AddAuthoritiesRequest;
import com.kurdi.authorizationserver.requests.UserNameAndPasswordAuthenticationRequest;
import com.kurdi.authorizationserver.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {
    @Autowired
    AuthService authService;

    @GetMapping("/admin")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public String admin() {
        return "admin";
    }

    @PostMapping("/register")
    public ResponseEntity<IdentityUser> register(@RequestBody UserNameAndPasswordAuthenticationRequest authenticationRequest) {

        IdentityUser user = authService.register(authenticationRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("addAuthorities")
    public ResponseEntity<IdentityUser> addAuthorities(@RequestBody AddAuthoritiesRequest addAuthoritiesRequest) {
        IdentityUser user = authService.addAuthorities(addAuthoritiesRequest.getUserId(), addAuthoritiesRequest.getAuthorities());
        if (user.getUserName() == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("removeAuthorities")
    public ResponseEntity<IdentityUser> removeAuthorities(@RequestBody AddAuthoritiesRequest addAuthoritiesRequest) {
        IdentityUser user = authService.removeAuthorities(addAuthoritiesRequest.getUserId(), addAuthoritiesRequest.getAuthorities());
        if (user.getUserName() == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
