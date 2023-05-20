package com.kurdi.authorizationserver.controllers;

import com.kurdi.authorizationserver.entities.Authority;
import com.kurdi.authorizationserver.entities.Project;
import com.kurdi.authorizationserver.entities.Module;
import com.kurdi.authorizationserver.entities.Action;

import com.kurdi.authorizationserver.repositories.AuthoritiesRepository;
import com.kurdi.authorizationserver.services.AuthService;
import com.kurdi.authorizationserver.services.AuthoritiesService;
import com.kurdi.authorizationserver.vm.actions.AddActionVM;
import com.kurdi.authorizationserver.vm.modules.AddModuleVM;
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
    AuthoritiesService authoritiesService;
    @Autowired
    AuthoritiesRepository authoritiesRepository;


    @PostMapping(value = "add-project")
    public ResponseEntity<Object> addProject(AddProjectVM addProjectVM)
    {
        try{
           authoritiesService.addProject(addProjectVM);
           return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "add-module")
    public ResponseEntity<Object> addModule(AddModuleVM addModuleVM)
    {
        try{
            authoritiesService.addModule(addModuleVM);
            return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "add-actions")
    public ResponseEntity<Object> addActions(List<AddActionVM> addActionVMs)
    {
        try{
            authoritiesService.addActions(addActionVMs);
           return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
