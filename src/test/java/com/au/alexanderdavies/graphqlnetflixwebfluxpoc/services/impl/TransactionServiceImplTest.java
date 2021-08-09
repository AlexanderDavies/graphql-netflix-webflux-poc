package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.services.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.TransactionDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Accounts;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Transactions;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.repository.TransactionRepository;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.impl.TransactionServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TransactionServiceImplTest {

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    ModelMapper modelMapper;

    private TransactionDto transactionDto;

    private Transactions transactionEntity;

    private String transactionId;

    private String accountId;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        transactionId = "hvQ3b0uom9H6LdgO90Q12345asdfg";
        accountId = "hvQ3b0uom9H6LdgO90Q12345asdfh";
        LocalDateTime transactionDate = LocalDateTime.now();
        Long transactionAmount = Long.valueOf(100);
        Long id = Long.valueOf(1);

        transactionDto = TransactionDto.builder().transactionId(transactionId).accountId(accountId)
                .date(transactionDate).amount(transactionAmount).build();

        transactionEntity = Transactions.builder().id(id).transactionId(transactionId).accountId(accountId)
                .date(transactionDate).amount(transactionAmount).build();

    }

    @Test
    @DisplayName("Transaction Service: getTransactions - success")
    public void testGetTransactions() {

        when(modelMapper.map(any(Transactions.class), any())).thenReturn(transactionDto);

        when(transactionRepository.findAllByAccountId(any())).thenReturn(Flux.just(transactionEntity));

        StepVerifier.create(transactionService.getTransactions(accountId)).expectNext(transactionDto).expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Transaction Service - createTransactions - success")
    public void testCteateTransactions() {

        when(modelMapper.addMappings(any())).thenReturn(null);

        when(modelMapper.map(any(Transactions.class), any())).thenReturn(transactionDto);

        when(transactionRepository.saveAll(anyList())).thenReturn(Flux.just(transactionEntity));

        when(modelMapper.map(any(TransactionDto.class), any())).thenReturn(transactionEntity);

        StepVerifier.create(transactionService.createTransactions(List.of(transactionDto))).expectNext(transactionDto).expectComplete()
                .verify();

    }

}
