package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebFluxSecurity {

    @Value("${security.username}")
    private String securityUsername;

    @Value("${security.password}")
    private String securityPassword;

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {

        log.info(passwordEncoder().encode(securityPassword));

        UserDetails user = User.withUsername(securityUsername).password(passwordEncoder().encode(securityPassword))
                .roles("ACCOUNTS", "TRANSACTIONS").build();

        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable().authorizeExchange()
                .pathMatchers("/graphiql*").permitAll()
                .anyExchange().authenticated().and().httpBasic().and().formLogin().disable().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
