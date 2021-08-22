package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.repository;

import java.util.List;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Transactions;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transactions, Long> {
    public Flux<Transactions> findAllByAccountId(String accountId);

    @Query("select * from transactions where account_id in (:accountIds)")
    public Flux<Transactions> findAllByAccountIds(@Param("accountIds") List<String> accountIds);
    
}
