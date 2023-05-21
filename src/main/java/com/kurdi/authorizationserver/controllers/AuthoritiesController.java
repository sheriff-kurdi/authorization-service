package com.kurdi.authorizationserver.controllers;



import com.kurdi.authorizationserver.repositories.AuthoritiesRepository;
import com.kurdi.authorizationserver.services.AuthService;
import com.kurdi.authorizationserver.services.AuthoritiesService;
import com.kurdi.authorizationserver.vm.actions.AddActionsVM;
import com.kurdi.authorizationserver.vm.modules.AddModuleVM;
import com.kurdi.authorizationserver.vm.projects.AddProjectVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("authorities/")
public class AuthoritiesController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthoritiesService authoritiesService;
    @Autowired
    AuthoritiesRepository authoritiesRepository;


    @PostMapping(value = "add-project")
    public ResponseEntity<Object> addProject(@RequestBody AddProjectVM addProjectVM)
    {
        try{
           authoritiesService.addProject(addProjectVM);
           return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "projects")
    public ResponseEntity<Object> listProjects()
    {
        try{
           authoritiesService.listProjects();
           return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "add-module")
    public ResponseEntity<Object> addModule(@RequestBody AddModuleVM addModuleVM)
    {
        try{
            authoritiesService.addModule(addModuleVM);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "modules")
    public ResponseEntity<Object> listModules()
    {
        try{
           authoritiesService.listProjects();
           return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "add-actions")
    public ResponseEntity<Object> addActions(@RequestBody AddActionsVM addActionsVM)
    {
        try{
            authoritiesService.addActions(addActionsVM);
           return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "actions")
    public ResponseEntity<Object> listActions()
    {
        try{
           authoritiesService.listActions();
           return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
