package br.com.rinhabackend.infraestructure.rest.exception;

import io.micronaut.context.annotation.Primary;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Produces
public class GenericExceptionHandler implements ExceptionHandler<Exception, HttpResponse<String>> {
    @Override
    public HttpResponse<String> handle(HttpRequest request, Exception exception) {
        log.error("An exception has ocurred on execute request [{}]", exception.getMessage());
        return HttpResponse.serverError().body(exception.getMessage());
    }
}
