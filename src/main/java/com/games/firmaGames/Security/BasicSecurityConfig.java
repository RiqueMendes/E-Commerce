package com.games.firmaGames.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BasicSecurityConfig {


    @Autowired
    private UserDetailsService userDetailsService;

   
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

 
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
        .antMatchers("/user/login").permitAll()
        .antMatchers("/user/register").permitAll()
        .anyRequest().authenticated()
        .and().httpBasic()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().cors()
        .and().csrf().disable();
    }
    
}

