package com.kurdi.authorizationserver.repositories;

import com.kurdi.authorizationserver.entities.IdentityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdentityUsersRepository extends JpaRepository<IdentityUser, Integer> {
    Optional<IdentityUser> findUserByUserName(String userName);

}
