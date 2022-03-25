package com.kurdi.authorizationserver.repositories;

import com.kurdi.authorizationserver.entities.Authority;
import com.kurdi.authorizationserver.entities.IdentityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authority, Integer> {
    Optional<IdentityUser> findUserByName(String userName);

}
