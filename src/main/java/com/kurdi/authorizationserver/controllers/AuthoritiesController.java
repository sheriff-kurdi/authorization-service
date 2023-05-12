package com.kurdi.authorizationserver.controllers;

import com.kurdi.authorizationserver.entities.Authority;
import com.kurdi.authorizationserver.repositories.AuthoritiesRepository;
import com.kurdi.authorizationserver.services.AuthService;
import com.kurdi.authorizationserver.vm.projects.AddProjectVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Authorities/")
public class AuthoritiesController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @GetMapping
    public ResponseEntity<List<Authority>> authorities()
    {
        return new ResponseEntity<>(authoritiesRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Authority> addAuthority(Authority authority)
    {

        return new ResponseEntity<>(authoritiesRepository.save(authority), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Authority> addProject(AddProjectVM request)
    {

        return new ResponseEntity<>(authoritiesRepository.save(authority), HttpStatus.OK);
    }



}
