package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "db")
public class DatabaseConfigProperties {

    private String url;
    private String username;
    private String password;
    
}
