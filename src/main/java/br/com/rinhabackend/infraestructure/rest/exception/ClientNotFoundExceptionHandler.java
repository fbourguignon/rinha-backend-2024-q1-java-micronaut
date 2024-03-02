package br.com.rinhabackend.infraestructure.rest.exception;

import br.com.rinhabackend.domain.exception.ClientNotFoundException;
import io.micronaut.context.annotation.Primary;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Produces
public class ClientNotFoundExceptionHandler implements ExceptionHandler<ClientNotFoundException, HttpResponse<String>> {

    @Override
    public HttpResponse<String> handle(HttpRequest request, ClientNotFoundException exception) {
        log.error("An client not found exception has occurred on execute request [{}]", exception.getMessage());
        return HttpResponse
                .notFound(exception.getMessage());
    }
}
