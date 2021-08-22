package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.TransactionDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Transactions;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.repository.TransactionRepository;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.TransactionService;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Flux<TransactionDto> getTransactionsForAccount(String accountId) {

        log.info("Fetching transactions for accountId: {}", accountId);

        return transactionRepository.findAllByAccountId(accountId)
                .map(transactionEntity -> modelMapper.map(transactionEntity, TransactionDto.class));

    }

    @Override
    public Flux<TransactionDto> getTransactionsForAccounts(List<String> accountIds) {

        log.info("Fetching transactions for accountIds: {}", accountIds);

        return transactionRepository.findAllByAccountIds(accountIds)
                .map(transactionEntity -> modelMapper.map(transactionEntity, TransactionDto.class));

    }

    @Override
    public Flux<TransactionDto> createTransactions(List<TransactionDto> transactions) {

        log.info("Saving transactions to store: {}", transactions);

        modelMapper.addMappings(new PropertyMap<TransactionDto, Transactions>() {
            @Override
            protected void configure() {
                skip().setId(null);
            }
        });

        List<Transactions> transactionEntities = transactions.stream()
                .map(transactionDto -> modelMapper.map(transactionDto, Transactions.class))
                .collect(Collectors.toList());

        return transactionRepository.saveAll(transactionEntities)
                .map(transactionEntity -> modelMapper.map(transactionEntity, TransactionDto.class));
    }

}
