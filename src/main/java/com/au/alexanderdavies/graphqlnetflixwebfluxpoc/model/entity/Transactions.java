package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    @Id
    private Long id = null;
    private String transactionId;
    private long amount;
    private LocalDateTime date;
    private String accountId;

}
