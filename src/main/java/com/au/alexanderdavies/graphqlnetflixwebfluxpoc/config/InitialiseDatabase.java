package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.AccountDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.CustomerDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.TransactionDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.AccountService;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.CustomerService;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

import io.r2dbc.h2.H2ConnectionFactory;
import reactor.test.StepVerifier;

@Component
public class InitialiseDatabase {

        @Autowired
        H2ConnectionFactory h2ConnectionFactory;

        @Autowired
        AccountService accountService;

        @Autowired
        TransactionService transactionService;

        @Autowired
        CustomerService customerService;

        @EventListener
        public void onApplicationEvent(ApplicationReadyEvent event) {

                List<String> statements = List.of(
                                "DROP TABLE IF EXISTS customers;",
                                "CREATE table customers (id INT AUTO_INCREMENT NOT NULL, CUSTOMER_ID VARCHAR(50) NOT NULL, FIRST_NAME VARCHAR(100) NOT NULL, SURNAME VARCHAR(50) NOT NULL);",
                                "DROP TABLE IF EXISTS accounts;",
                                "CREATE table accounts (id INT AUTO_INCREMENT NOT NULL, ACCOUNT_ID VARCHAR(50) NOT NULL, ACCOUNT_NAME VARCHAR(100) NOT NULL, BALANCE BIGINT NOT NULL, CUSTOMER_ID VARCHAR(50) NOT NULL);",
                                "DROP TABLE IF EXISTS transactions;",
                                "CREATE table transactions (id INT AUTO_INCREMENT NOT NULL, TRANSACTION_ID VARCHAR(50) NOT NULL, AMOUNT BIGINT NOT NULL, DATE TIMESTAMP(9) NOT NULL, ACCOUNT_ID VARCHAR(50) NOT NULL);");

                R2dbcEntityTemplate template = new R2dbcEntityTemplate(h2ConnectionFactory);

                statements.forEach(it -> template.getDatabaseClient().sql(it).fetch().rowsUpdated()
                                .as(StepVerifier::create).expectNextCount(1).verifyComplete());

                String customerId = "hvQ3b0uom9H6LdgO90Q12345asdf1";

                CustomerDto customerDto = CustomerDto.builder().customerId(customerId)
                                .firstName("test").surname("user").build();

                List<String> accountIds = List.of("hvQ3b0uom9H6LdgO90Q12345asdfg", "CJfjGezjJl26pCc84yM812345asdfg",
                                "pHbJBXxMVjHWSyZQLE1J12345asdfg", "L07Mo4I6V59RF4A76s5w12345asdfg",
                                "eugsM0sALcIaGQ9ALSvG12345asdfg", "1ibhMhD8GbG4thDbXJVv12345asdfg");

                List<AccountDto> accounts = new ArrayList<>();

                List<TransactionDto> transactions = new ArrayList<>();

                accountIds.forEach(accountId -> {

                        AccountDto accountDetails = AccountDto.builder()
                                        .accountName("Test Account: " + ThreadLocalRandom.current().nextLong())
                                        .balance(ThreadLocalRandom.current().nextLong()).accountId(accountId)
                                        .customerId(customerId)
                                        .build();

                        accounts.add(accountDetails);

                        for (int i = 0; i < 3; i++) {
                                transactions.add(TransactionDto.builder().transactionId(UUID.randomUUID().toString())
                                                .accountId(accountId).amount(ThreadLocalRandom.current().nextLong())
                                                .date(LocalDateTime.now()).build());
                        }

                });

                customerService.createCustomers(List.of(customerDto)).subscribe();

                accountService.createAccounts(accounts).subscribe();

                transactionService.createTransactions(transactions).subscribe();

        }

}
