package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service;

import java.util.List;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.TransactionDto;

import reactor.core.publisher.Flux;

public interface TransactionService {
    public Flux<TransactionDto> getTransactionsForAccount(String accountId);
    public Flux<TransactionDto> getTransactionsForAccounts(List<String> accountIds);
    public Flux<TransactionDto> createTransactions(List<TransactionDto> transactions);
    
}
