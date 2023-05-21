package com.kurdi.authorizationserver.services;

import com.kurdi.authorizationserver.entities.Project;
import com.kurdi.authorizationserver.entities.Module;
import com.kurdi.authorizationserver.entities.Action;
import com.kurdi.authorizationserver.entities.Authority;
import com.kurdi.authorizationserver.repositories.ModulesRepository;
import com.kurdi.authorizationserver.repositories.ProjectsRepository;
import com.kurdi.authorizationserver.repositories.ActionsRepository;
import com.kurdi.authorizationserver.repositories.AuthoritiesRepository;
import com.kurdi.authorizationserver.vm.actions.ActionVM;
import com.kurdi.authorizationserver.vm.actions.AddActionVM;
import com.kurdi.authorizationserver.vm.actions.AddActionsVM;
import com.kurdi.authorizationserver.vm.modules.AddModuleVM;
import com.kurdi.authorizationserver.vm.modules.ModuleVM;
import com.kurdi.authorizationserver.vm.projects.AddProjectVM;
import com.kurdi.authorizationserver.vm.projects.ProjectVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthoritiesService {

    @Autowired
    ProjectsRepository projectsRepository;
    @Autowired
    ModulesRepository modulesRepository;
    @Autowired
    ActionsRepository actionsRepository;
    @Autowired
    AuthoritiesRepository authoritiessRepository;

    public Project addProject(AddProjectVM addProjectVM) {
        Project project = Project.builder()
                .name(addProjectVM.getName())
                .description(addProjectVM.getDescription())
                .build();

        return this.projectsRepository.save(project);
    }

    public List<ProjectVM> listProjects() {
        return this.projectsRepository.findAll().stream()
                .map(project -> ProjectVM.builder()
                        .name(project.getName())
                        .description(project.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public Module addModule(AddModuleVM addModuleVM) {
        Module module = Module.builder()
                .name(addModuleVM.getName())
                .description(addModuleVM.getDescription())
                .project(projectsRepository.getById(addModuleVM.getProjectName()))
                .build();

        return this.modulesRepository.save(module);
    }

    public List<ModuleVM> listModules() {
        return this.modulesRepository.findAll().stream()
                .map(module -> ModuleVM.builder()
                        .name(module.getName())
                        .description(module.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Authority> addActions(AddActionsVM addActionsVM) {
        Module module = this.modulesRepository.getById(addActionsVM.getModuleName());
        List<Action> actions = new ArrayList<>();
        List<Authority> authorities = new ArrayList<>();

        for (AddActionVM addActionVM : addActionsVM.getActions()) {
            Action action = Action.builder()
                    .name(addActionVM.getName())
                    .description(addActionVM.getDescription())
                    .module(modulesRepository.getById(addActionsVM.getModuleName()))
                    .build();

            actions.add(action);

            Authority authority = Authority.builder()
                    .module(module)
                    .project(module.getProject())
                    .action(action)
                    .build();

            authority.setName();

            authorities.add(authority);
        }

        this.actionsRepository.saveAll(actions);
        return this.authoritiessRepository.saveAll(authorities);
    }

    public List<ActionVM> listActions() {
        return this.modulesRepository.findAll().stream()
                .map(action -> ActionVM.builder()
                        .name(action.getName())
                        .description(action.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
