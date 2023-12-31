package com.drunkleen.crudmvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}123")
                .roles("VIEW")
                .build();

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}123")
                .roles("WRITER", "VIEW")
                .build();

        UserDetails leen = User.builder()
                .username("leen")
                .password("{noop}123")
                .roles("ADMIN", "WRITER", "VIEW")
                .build();

        return new InMemoryUserDetailsManager(susan, john, leen);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/auth/logout") 
                        .logoutSuccessUrl("/auth/login?logout")
                        .permitAll());

        return http.build();
    }
}
