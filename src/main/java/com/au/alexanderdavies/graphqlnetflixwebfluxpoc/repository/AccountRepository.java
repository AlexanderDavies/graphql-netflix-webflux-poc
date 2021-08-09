package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.repository;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Accounts;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<Accounts, Long> {
    
    @Query("select *  from accounts where ACCOUNT_ID = $1")
    public Mono<Accounts> findByAccountId(String accountId);
}
