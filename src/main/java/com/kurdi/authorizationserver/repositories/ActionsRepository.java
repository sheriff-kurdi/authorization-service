package com.kurdi.authorizationserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kurdi.authorizationserver.entities.Action;



public interface ActionsRepository extends JpaRepository<Action, String> {

}
