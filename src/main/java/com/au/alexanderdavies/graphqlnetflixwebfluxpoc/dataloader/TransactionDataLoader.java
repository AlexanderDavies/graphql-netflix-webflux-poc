package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.dataloader;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.TransactionDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.TransactionService;
import com.netflix.graphql.dgs.DgsDataLoader;

import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Flux;

@DgsDataLoader(name = "transactions")
public class TransactionDataLoader implements BatchLoader<String, List<TransactionDto>> {

    @Autowired
    TransactionService transactionService;

    @Override
    public CompletableFuture<List<List<TransactionDto>>> load(List<String> accountIds) {

        return Flux.merge(transactionService.getTransactionsForAccounts(accountIds)).collectList()
                .map(transactions -> accountIds.stream()
                        .map(accountId -> transactions.stream()
                                .filter(transaction -> transaction.getAccountId().equals(accountId))
                                .collect(Collectors.toList()))
                        .collect(Collectors.toList()))
                .toFuture();
    }

}
