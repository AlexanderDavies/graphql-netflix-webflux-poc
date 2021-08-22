package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service;

import java.util.List;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.CustomerDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    public Mono<CustomerDto> getCustomer(String customerId);
    public Flux<CustomerDto>  createCustomers(List<CustomerDto> customers);
}
