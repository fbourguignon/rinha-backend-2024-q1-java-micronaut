package br.com.rinhabackend.infraestructure.rest.controller;

import br.com.rinhabackend.application.ClientService;
import br.com.rinhabackend.application.TransactionService;
import br.com.rinhabackend.domain.model.Client;
import br.com.rinhabackend.infraestructure.rest.request.CreateTransactionRequest;
import br.com.rinhabackend.infraestructure.rest.response.CreateTransactionResponse;
import br.com.rinhabackend.infraestructure.rest.response.ExtractResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

@Controller("/clientes")
@ExecuteOn(TaskExecutors.BLOCKING)
public class ClientController {

    private final TransactionService transactionService;
    private final ClientService clientService;

    public ClientController(TransactionService transactionService, ClientService clientService) {
        this.transactionService = transactionService;
        this.clientService = clientService;
    }

    @Post("/{id}/transacoes")
    public HttpResponse<CreateTransactionResponse> createTransaction(@PathVariable(name = "id") Integer clientId, @Body @Valid CreateTransactionRequest request){
        transactionService.createTransaction(clientId, request.value(),request.type(), request.description());
        final Client client = clientService.getClientById(clientId);
        return HttpResponse.ok(new CreateTransactionResponse(client.limit(), client.balance()));
    }

    @Get("/{id}/transacoes")
    public HttpResponse<ExtractResponse> retrieveExtract(@PathVariable(name = "id") Integer clientId){
        return null;
    }

}

