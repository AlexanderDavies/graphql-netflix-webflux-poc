package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.datafetcher;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.AccountDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.TransactionDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.TransactionService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;

import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import graphql.schema.DataFetchingEnvironment;

@DgsComponent
public class TransactionDataFetcher {

    @Autowired
    TransactionService transactionService;

    @DgsData(parentType = "Account", field = "transactions")
    public CompletableFuture<List<TransactionDto>> getTransactions(DataFetchingEnvironment dfe) {
        DataLoader<String, List<TransactionDto>> dataLoader = dfe.getDataLoader("transactions");

        AccountDto accountDto = dfe.getSource();

        return dataLoader.load(accountDto.getAccountId());
    }
}
