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
public class Customers {

    @Id
    private Long id = null;
    private String customerId;
    private String firstName;
    private String surname;

}
