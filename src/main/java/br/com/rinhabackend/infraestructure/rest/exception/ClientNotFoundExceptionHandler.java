package br.com.rinhabackend.infraestructure.rest.exception;

import br.com.rinhabackend.domain.exception.ClientNotFoundException;
import io.micronaut.context.annotation.Primary;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

@Primary
@Produces
public class ClientNotFoundExceptionHandler implements ExceptionHandler<ClientNotFoundException, HttpResponse<String>> {

    @Override
    public HttpResponse<String> handle(HttpRequest request, ClientNotFoundException exception) {
        return HttpResponse
                .notFound(exception.getMessage());
    }
}
