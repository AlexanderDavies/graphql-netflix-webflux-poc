package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import static org.springframework.security.config.Customizer.withDefaults;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    @Value("${security.username}")
    private String securityUsername;

    @Value("${security.password}")
    private String securityPassword;

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {

        UserDetails user = User.withUsername(securityUsername).password(passwordEncoder().encode(securityPassword))
                .roles("ACCOUNTS", "TRANSACTIONS").build();

        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        log.info("debugging with security context");

        http.csrf().disable().authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance()).httpBasic(withDefaults());

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
