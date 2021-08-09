package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.model.entity;

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
public class Accounts {

    @Id
    private Long id = null;
    private String accountId;
    private String accountName;
    private long balance;

}
