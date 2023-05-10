package com.kurdi.authorizationserver.repositories;

import com.kurdi.authorizationserver.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectsRepository extends JpaRepository<Project, String> {

}
