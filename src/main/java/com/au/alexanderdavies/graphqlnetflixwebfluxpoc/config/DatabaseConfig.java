package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;

@Configuration  
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Autowired
    DatabaseConfigProperties dbConfigProps;
    
    @Bean
    public H2ConnectionFactory connectionFactory() {

        return new H2ConnectionFactory(
            H2ConnectionConfiguration.builder()
              .url(dbConfigProps.getUrl())
              .username(dbConfigProps.getUrl())
              .password(dbConfigProps.getPassword())
              .build()
        );
    }
}
