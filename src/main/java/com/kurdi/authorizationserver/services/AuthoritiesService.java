package com.kurdi.authorizationserver.services;


import com.kurdi.authorizationserver.entities.Project;
import com.kurdi.authorizationserver.entities.Module;
import com.kurdi.authorizationserver.entities.Action;


import com.kurdi.authorizationserver.repositories.ModulesRepository;
import com.kurdi.authorizationserver.repositories.ProjectsRepository;
import com.kurdi.authorizationserver.repositories.ActionsRepository;

import com.kurdi.authorizationserver.vm.actions.AddActionVM;
import com.kurdi.authorizationserver.vm.modules.AddModuleVM;
import com.kurdi.authorizationserver.vm.projects.AddProjectVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthoritiesService {

    @Autowired
    ProjectsRepository projectsRepository;
    @Autowired
    ModulesRepository modulesRepository;
    @Autowired
    ActionsRepository actionsRepository;


    public Project addProject(AddProjectVM addProjectVM) {
        Project project = Project.builder()
        .name(addProjectVM.getName())
        .description(addProjectVM.getDescription())
        .build();

        return this.projectsRepository.save(project);
    }

    public Module addModule(AddModuleVM addModuleVM) {
        Module module = Module.builder()
        .name(addModuleVM.getName())
        .description(addModuleVM.getDescription())
        .project(projectsRepository.getById(addModuleVM.getProjectName()))
        .build();

        return this.modulesRepository.save(module);
    }

    public List<Action> addAction(List<AddActionVM> addActionVMs) {
        List<Action> actions = new ArrayList<>();

        for (AddActionVM addActionVM : addActionVMs) {
            Action action = Action.builder()
            .name(addActionVM.getName())
            .description(addActionVM.getDescription())
            .module(modulesRepository.getById(addActionVM.getModuleName()))
            .build();

            actions.add(action);
        }


        return this.actionsRepository.saveAll(actions);
    }


}
