package br.com.rinhabackend.infraestructure.rest.controller;

import br.com.rinhabackend.application.ClientService;
import br.com.rinhabackend.application.TransactionService;
import br.com.rinhabackend.domain.model.Client;
import br.com.rinhabackend.domain.model.Transaction;
import br.com.rinhabackend.infraestructure.rest.request.CreateTransactionRequest;
import br.com.rinhabackend.infraestructure.rest.response.BalanceResponse;
import br.com.rinhabackend.infraestructure.rest.response.CreateTransactionResponse;
import br.com.rinhabackend.infraestructure.rest.response.ExtractResponse;
import br.com.rinhabackend.infraestructure.rest.response.TransactionResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;

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

    @Get("/{id}/extrato")
    public HttpResponse<ExtractResponse> retrieveExtract(@PathVariable(name = "id") Integer clientId){

        final Client client = clientService.getClientById(clientId);

        return HttpResponse.ok(new ExtractResponse(
                new BalanceResponse(client.balance(), LocalDateTime.now(), client.limit()),
                client.transactions()
                        .stream()
                        .sorted(Comparator.comparing(Transaction::createdAt).reversed())
                        .map(transaction -> new TransactionResponse(transaction.amount(),
                                transaction.type().name(),transaction.description(), transaction.createdAt()))
                        .collect(Collectors.toList())
        ));
    }

}

