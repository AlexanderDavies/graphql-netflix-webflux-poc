package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.repository;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Transactions;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transactions, Long> {
    public Flux<Transactions> findAllByAccountId(String transactionId);
    
}
