package com.au.alexanderdavies.graphqlnetflixwebfluxpoc.exception;

import java.util.HashMap;
import java.util.Map;

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import com.netflix.graphql.types.errors.ErrorType;
import com.netflix.graphql.types.errors.TypedGraphQLError;

import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;

@Component
public class ExceptionHanlder implements DataFetcherExceptionHandler {

    private final DefaultDataFetcherExceptionHandler defaultHandler = new DefaultDataFetcherExceptionHandler();

    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
        if (handlerParameters.getException() instanceof HttpException) {
            HttpException httpException = (HttpException) handlerParameters.getException();
            Map<String, Object> debugInfo = new HashMap<>();
            debugInfo.put("errorCode", httpException.getHttpStatus().toString());

            GraphQLError graphqlError = TypedGraphQLError.newBuilder().message(httpException.getMessage())
                    .errorType(ErrorType.INTERNAL).debugInfo(debugInfo).path(handlerParameters.getPath()).build();
            return DataFetcherExceptionHandlerResult.newResult().error(graphqlError).build();
        } else {
            return defaultHandler.onException(handlerParameters);
        }
    }
}
