package com.kurdi.authorizationserver.auth;


import com.kurdi.authorizationserver.entities.User;
import com.kurdi.authorizationserver.repositories.IdentityUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    IdentityUsersRepository usersRepository;


    @Override
    public CustomUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Supplier<UsernameNotFoundException> usernameNotFoundExceptionSupplier =
                () -> new UsernameNotFoundException("Problem during authentication!");

        User u = usersRepository
                .findUserByUserName(userName)
                .orElseThrow(usernameNotFoundExceptionSupplier);


        return new CustomUserDetails(u);
    }
}
