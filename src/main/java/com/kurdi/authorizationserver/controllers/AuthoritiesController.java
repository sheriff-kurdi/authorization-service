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
    public ResponseEntity<Project> addProject(AddProjectVM addProjectVM)
    {
        try{
           Project project = authoritiesService.addProject(addProjectVM);
           return new ResponseEntity<>(project, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Module> addModule(AddModuleVM addModuleVM)
    {
        try{
            Module module = authoritiesService.addModule(addModuleVM);
           return new ResponseEntity<>(module, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<List<Action>> addActions(List<AddActionVM> addActionVMs)
    {
        try{
            List<Action> actions = authoritiesService.addActions(addActionVMs);
           return new ResponseEntity<>(actions, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
