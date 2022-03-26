package com.kurdi.authorizationserver.config;


import com.kurdi.authorizationserver.auth.CustomAuthenticationProvider;
import com.kurdi.authorizationserver.auth.filters.CustomUserNameAuthenticationFilter;
import com.kurdi.authorizationserver.auth.filters.JwtTokenVerifierFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        http.formLogin();
*/
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new CustomUserNameAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifierFilter(), CustomUserNameAuthenticationFilter.class)
                .authorizeRequests()
                .mvcMatchers("/user")
                .permitAll()
                .mvcMatchers("/admin")
                .hasAnyAuthority("admin");


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

}
