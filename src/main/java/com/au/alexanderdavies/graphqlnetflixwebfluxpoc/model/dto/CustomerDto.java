package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String customerId;
    private String firstName;
    private String surname;
     
}
