package br.com.rinhabackend.infraestructure.rest.controller;

import br.com.rinhabackend.application.TransactionService;
import br.com.rinhabackend.infraestructure.rest.request.CreateTransactionRequest;
import br.com.rinhabackend.infraestructure.rest.response.CreateTransactionResponse;
import br.com.rinhabackend.infraestructure.rest.response.ExtractResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

@Controller("/clientes")
@ExecuteOn(TaskExecutors.IO)
public class ClientController {

    private final TransactionService service;

    public ClientController(TransactionService service) {
        this.service = service;
    }

    @Post("/{id}/transacoes")
    public HttpResponse<CreateTransactionResponse> createTransaction(@PathVariable(name = "id") Integer clientId, @Body @Valid CreateTransactionRequest request){
        return null;
    }

    @Get("/{id}/transacoes")
    public HttpResponse<ExtractResponse> retrieveExtract(@PathVariable(name = "id") Integer clientId){
        return null;
    }

}

