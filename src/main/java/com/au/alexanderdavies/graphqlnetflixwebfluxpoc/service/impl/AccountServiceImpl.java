package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.exception.HttpException;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.AccountDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Accounts;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.repository.AccountRepository;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Mono<AccountDto> getAccount(String accountId) {

        log.info("Fetching account: {}", accountId);

        return accountRepository.findByAccountId(accountId)
                .switchIfEmpty(Mono.error(new HttpException("account doesn't exist!", HttpStatus.NOT_FOUND)))
                .map(accountEntity -> modelMapper.map(accountEntity, AccountDto.class));
    }

    @Override
    public Flux<AccountDto> getAccounts(String customerId) {

        log.info("Fetching accounts for customerId: {}", customerId);

        return accountRepository.findAllByCustomerId(customerId)
                .map(accountEntity -> modelMapper.map(accountEntity, AccountDto.class));
    }


    @Override
    public Flux<AccountDto> createAccounts(List<AccountDto> accounts) {

        log.info("Saving accounts to store: {}", accounts);

        modelMapper.addMappings(new PropertyMap<AccountDto, Accounts>() {
            @Override
            protected void configure() {
                skip().setId(null);
            }
        });

        List<Accounts> accountEntities = accounts.stream()
                .map(accountDto -> modelMapper.map(accountDto, Accounts.class)).collect(Collectors.toList());

        return accountRepository.saveAll(accountEntities).map(accountEntity -> modelMapper.map(accountEntity, AccountDto.class));

    }

}
