package com.kurdi.authorizationserver.auth.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurdi.authorizationserver.entities.User;
import com.kurdi.authorizationserver.repositories.IdentityUsersRepository;
import com.kurdi.authorizationserver.requests.UserNameAndPasswordAuthenticationRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;

public class CustomUserNameAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    AuthenticationManager authenticationManager;
    IdentityUsersRepository identityUsersRepository;
    String userId;


    public CustomUserNameAuthenticationFilter(AuthenticationManager authenticationManager, IdentityUsersRepository identityUsersRepository) {

        this.authenticationManager = authenticationManager;
        this.identityUsersRepository = identityUsersRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UserNameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UserNameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            userId = authentication.getName();

            Authentication authenticate = authenticationManager.authenticate(authentication);

            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        //TODO:from config
        /*TODO:
         * secret key from consumer
         * To make it secure so only consumer service that can decode that token to get authorities
         * */


        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
        Key key = new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());

        Object principal = authResult.getPrincipal();
        String username = principal.toString();

        User identityUser = identityUsersRepository.findUserByUserName(username).get();

        //TODO: Put user authorities
        String jwtToken = Jwts.builder()
                .setId(identityUser.getId().toString())
                .claim("authorities", authResult.getAuthorities())
                .setSubject(identityUser.getUserName()).signWith(key).compact();


        response.addHeader("Authentication", jwtToken);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"" + "token" + "\":\"" + jwtToken + "\"}"
        );
    }
}
