package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.datafetcher;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto.CustomerDto;
import com.au.alexanderdavies.graphqlnetflixwebfluxpoc.service.CustomerService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Mono;

@DgsComponent
public class CustomerDataFetcher {

    @Autowired
    CustomerService customerService;

    @DgsData(parentType="Query", field="customer")
    public Mono<CustomerDto> getAccount(@InputArgument("customerId") String customerId) {
        return customerService.getCustomer(customerId);

    }
}
