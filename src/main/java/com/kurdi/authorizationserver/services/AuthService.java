package com.kurdi.authorizationserver.services;


import com.kurdi.authorizationserver.entities.Authority;
import com.kurdi.authorizationserver.entities.User;
import com.kurdi.authorizationserver.repositories.IdentityUsersRepository;
import com.kurdi.authorizationserver.requests.UserNameAndPasswordAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    IdentityUsersRepository usersRepository;

    public User register(UserNameAndPasswordAuthenticationRequest authenticationRequest) {
        Set<Authority> authorities = new HashSet<>();

        User user = User.builder()
                .userName(authenticationRequest.getUsername())
                .password(passwordEncoder.encode(authenticationRequest.getPassword()))
                .authorities(authorities)
                .build();


        if (usersRepository.findUserByUserName(user.getUserName()).isEmpty()) {
            //TODO when creating user make an app event to create his cart instead of making it here.
            usersRepository.save(user);
        }

        return User.builder()
                .userName(user.getUserName())
                .id(user.getId()).build();
    }

    public User login(UserNameAndPasswordAuthenticationRequest authenticationRequest) {
        Set<Authority> authorities = new HashSet<>();

        User user = User.builder()
                .userName(authenticationRequest.getUsername())
                .password(passwordEncoder.encode(authenticationRequest.getPassword()))
                .authorities(authorities)
                .build();


        if (usersRepository.findUserByUserName(user.getUserName()).isEmpty()) {
            return null;
        } else {

        }

        return usersRepository.findUserByUserName(user.getUserName()).get();
    }

    public User addAuthorities(Integer userId, Set<Authority> authorities) {
        User user = usersRepository.getById(userId);
        if (user == null) {
            //TODO:return domain exception
            return null;
        }
        user.getAuthorities().addAll(authorities);

        usersRepository.save(user);
        return usersRepository.getById(userId);
    }

    public User removeAuthorities(Integer userId, Set<Authority> authorities) {
        User user = usersRepository.getById(userId);
        if (user == null) {
            //TODO:return domain exception
            return null;
        }


        List<Authority> usersAuthTORemove = user.getAuthorities()
                .stream()
                .filter(userAuth -> authorities.stream()
                        .anyMatch(requestAuth -> requestAuth.getName().equalsIgnoreCase(userAuth.getName())))
                .collect(Collectors.toList());

        user.getAuthorities().removeAll(usersAuthTORemove);
        usersRepository.save(user);
        return usersRepository.getById(userId);
    }

}
