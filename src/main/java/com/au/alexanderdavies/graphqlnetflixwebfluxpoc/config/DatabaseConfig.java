package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;

@Configuration  
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Value("{db.url}")
    private String dbUrl;

    @Value("{db.password}")
    private String dbPassword;

    @Value("{db.username}")
    private String dbUsername;

    @Bean
    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
            H2ConnectionConfiguration.builder()
              .url("mem:testdb;DB_CLOSE_DELAY=-1;")
              .username("admin")
              .password("test1234")
              .build()
        );
    }
}
