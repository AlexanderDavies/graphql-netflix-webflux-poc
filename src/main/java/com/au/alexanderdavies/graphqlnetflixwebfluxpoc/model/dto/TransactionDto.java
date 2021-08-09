package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.dto;

import java.time.LocalDateTime;

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
public class TransactionDto {
 
    private String transactionId;
    private long amount;
    private LocalDateTime date;
    private String accountId;

}
