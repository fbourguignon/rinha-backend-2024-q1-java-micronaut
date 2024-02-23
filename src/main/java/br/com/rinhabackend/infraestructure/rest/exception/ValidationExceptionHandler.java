package br.com.rinhabackend.infraestructure.rest.exception;

import br.com.rinhabackend.infraestructure.rest.response.ValidationErrorResponse;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.stream.Collectors;

@Primary
@Produces
@Replaces(io.micronaut.validation.exceptions.ConstraintExceptionHandler.class)
@Requires(classes = {ConstraintViolationException.class, ExceptionHandler.class})
public class ValidationExceptionHandler implements ExceptionHandler<ConstraintViolationException, HttpResponse<ValidationErrorResponse>> {

    @Override
    public HttpResponse<ValidationErrorResponse> handle(HttpRequest request, ConstraintViolationException exception) {
        return HttpResponse
                .badRequest()
                .body(new ValidationErrorResponse(exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList())));
    }
}
