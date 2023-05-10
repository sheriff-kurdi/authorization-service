package com.kurdi.authorizationserver.repositories;

import com.kurdi.authorizationserver.entities.Authority;
import com.kurdi.authorizationserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authority, Integer> {
    Optional<User> findUserByName(String userName);

}
