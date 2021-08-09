package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.datafetcher;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.TransactionDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.TransactionService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Flux;

@DgsComponent
public class TransactionDataFetcher {
    
    @Autowired
    TransactionService transactionService;

    @DgsData(parentType="Query", field="transactions")
    public Flux<TransactionDto> getTransactions(@InputArgument("accountId") String accountId) {
        return transactionService.getTransactions(accountId);
    }
}
