package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.repository;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Accounts;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<Accounts, Long> {
    
    public Mono<Accounts> findByAccountId(String accountId);

    public Flux<Accounts> findAllByCustomerId(String customerId);
}
