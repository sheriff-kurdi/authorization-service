package com.kurdi.authorizationserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kurdi.authorizationserver.entities.Module;


public interface ModulesRepository extends JpaRepository<Module, String> {

}
