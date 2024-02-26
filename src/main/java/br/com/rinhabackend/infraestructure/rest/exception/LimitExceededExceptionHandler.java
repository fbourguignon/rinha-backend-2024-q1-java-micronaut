package br.com.rinhabackend.infraestructure.rest.exception;

import br.com.rinhabackend.domain.exception.LimitExceededException;
import io.micronaut.context.annotation.Primary;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Produces
public class LimitExceededExceptionHandler implements ExceptionHandler<LimitExceededException, HttpResponse<String>> {

    @Override
    public HttpResponse<String> handle(HttpRequest request, LimitExceededException exception) {
        log.error("An limit exceeded exception has occurred on execute request [{}]", exception.getMessage());
        return HttpResponse.unprocessableEntity().body(exception.getMessage());
    }
}