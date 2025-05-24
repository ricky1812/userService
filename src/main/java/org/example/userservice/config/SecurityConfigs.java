package org.example.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfigs {


  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      //  .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs
        .authorizeHttpRequests(auth -> auth
          // .requestMatchers("users/signup", "/login").permitAll() // Public endpoints
            .anyRequest().authenticated() // All other endpoints require authentication
        )
        .httpBasic(); // Or formLogin(), depending on your use case

    return http.build();
  }


}
