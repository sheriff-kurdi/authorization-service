package com.kurdi.authorizationserver.repositories;

import com.kurdi.authorizationserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdentityUsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUserName(String userName);

}
