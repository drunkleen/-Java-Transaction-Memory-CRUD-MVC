package com.drunkleen.crudmvc.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, roles.role FROM roles WHERE username=?");

        return jdbcUserDetailsManager;
    }
    // @Bean
    // public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

    // UserDetails susan = User.builder()
    // .username("susan")
    // .password("{noop}123")
    // .roles("VIEW")
    // .build();

    // UserDetails john = User.builder()
    // .username("john")
    // .password("{noop}123")
    // .roles("WRITER", "VIEW")
    // .build();

    // UserDetails leen = User.builder()
    // .username("leen")
    // .password("{noop}123")
    // .roles("ADMIN", "WRITER", "VIEW")
    // .build();

    // return new InMemoryUserDetailsManager(susan, john, leen);
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/transactions").hasRole("VIEW")
                .requestMatchers("/transactions", "transactions/add-transaction").hasRole("WRITER")
                .requestMatchers("/transactions", "transactions/**").hasRole("ADMIN")
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
