package com.kurdi.authorizationserver.repositories;

import com.kurdi.authorizationserver.entities.IdentityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IdentityUsersRepository extends JpaRepository<IdentityUser, Integer> {
    Optional<IdentityUser> findUserByUserName(String userName);

}
