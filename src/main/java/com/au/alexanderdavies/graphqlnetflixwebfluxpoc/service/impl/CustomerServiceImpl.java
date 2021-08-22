package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.exception.HttpException;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.CustomerDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity.Customers;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.repository.CustomerRepository;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Mono<CustomerDto> getCustomer(String customerId) {

        log.info("Fetching customer: {}", customerId);

        return customerRepository.findByCustomerId(customerId)
                .switchIfEmpty(Mono.error(new HttpException("customer doesn't exist!", HttpStatus.NOT_FOUND)))
                .map(customerEntity -> modelMapper.map(customerEntity, CustomerDto.class));
    }

    @Override
    public Flux<CustomerDto> createCustomers(List<CustomerDto> customers) {

        log.info("Saving customers to store: {}", customers);

        modelMapper.addMappings(new PropertyMap<CustomerDto, Customers>() {
            @Override
            protected void configure() {
                skip().setId(null);
            }
        });

        List<Customers> customerEntities = customers.stream()
                .map(customerDto -> modelMapper.map(customerDto, Customers.class)).collect(Collectors.toList());

        return customerRepository.saveAll(customerEntities).map(customerEntity -> modelMapper.map(customerEntity, CustomerDto.class));

    }

}
