package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.services.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.List;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.AccountDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Accounts;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.repository.AccountRepository;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.impl.AccountServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    ModelMapper modelMapper;

    private String accountId;

    private String customerId;

    private AccountDto accountDto;

    private Accounts accountEntity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        accountId = "hvQ3b0uom9H6LdgO90Q12345asdfg";
        customerId = "hvQ3b0uom9H6LdgO90Q12345asdf1";
        String accountName = "Test 151324";
        int balance = 1000;

        accountDto = AccountDto.builder().accountId(accountId).accountName(accountName).balance(balance).build();

        accountEntity = Accounts.builder().accountId(accountId).accountName(accountName).balance(balance).build();

    }

    @Test
    @DisplayName("AccountsService: getAccount - success")
    public void testGetAccount() {

        when(modelMapper.map(any(Accounts.class), any())).thenReturn(accountDto);

        when(accountRepository.findByAccountId(any())).thenReturn(Mono.just(accountEntity));

        StepVerifier.create(accountService.getAccount(accountId)).expectNext(accountDto).expectComplete().verify();

    }

    @Test
    @DisplayName("AccountsService: getAccounts - success")
    public void testGetAccounts() {

        when(modelMapper.map(any(Accounts.class), any())).thenReturn(accountDto);

        when(accountRepository.findAllByCustomerId(any())).thenReturn(Flux.just(accountEntity));

        StepVerifier.create(accountService.getAccounts(customerId)).expectNext(accountDto).expectComplete().verify();

    }

    @Test
    @DisplayName("AccountsService: createAccounts - success")
    public void testCreateAccounts() {

        when(modelMapper.addMappings(any())).thenReturn(null);

        when(modelMapper.map(any(AccountDto.class), any())).thenReturn(accountEntity);

        when(accountRepository.saveAll(anyList())).thenReturn(Flux.just(accountEntity));

        when(modelMapper.map(any(Accounts.class), any())).thenReturn(accountDto);

        StepVerifier.create(accountService.createAccounts(List.of(accountDto))).expectNext(accountDto).expectComplete().verify();

    }


}
