package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.repository;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Customers;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customers, Long> {
    
    public Mono<Customers> findByCustomerId(String customerId);
}
