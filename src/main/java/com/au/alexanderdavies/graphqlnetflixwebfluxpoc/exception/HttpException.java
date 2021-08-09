package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class HttpException extends Exception {

    private final HttpStatus httpStatus;

    public HttpException(String message, HttpStatus httpStatus) {

        super(message);

        this.httpStatus = httpStatus;

    }
}
