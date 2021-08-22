package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service;

import java.util.List;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.AccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    public Mono<AccountDto> getAccount(String accountId);
    public Flux<AccountDto> getAccounts(String customerId);
    public Flux<AccountDto>  createAccounts(List<AccountDto> accounts);
}
