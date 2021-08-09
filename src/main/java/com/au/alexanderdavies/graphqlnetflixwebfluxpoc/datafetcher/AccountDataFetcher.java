package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.datafetcher;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.AccountDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.AccountService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Mono;

@DgsComponent
public class AccountDataFetcher {

    @Autowired
    AccountService accountService;

    @DgsData(parentType="Query", field="account")
    public Mono<AccountDto> getAccount(@InputArgument("accountId") String accountId) {
        return accountService.getAccount(accountId);
    }
}
