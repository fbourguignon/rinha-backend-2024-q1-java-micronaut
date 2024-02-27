package br.com.rinhabackend.application;

import br.com.rinhabackend.domain.exception.ClientNotFoundException;
import br.com.rinhabackend.domain.model.Client;
import br.com.rinhabackend.domain.model.Transaction;
import br.com.rinhabackend.domain.model.TransactionType;
import br.com.rinhabackend.infraestructure.persistence.ClientRepository;
import br.com.rinhabackend.infraestructure.persistence.TransactionRepository;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;


import java.util.List;

import static br.com.rinhabackend.domain.usecase.CalculateNewBalanceUseCase.calculateNewBalance;

@Singleton
public class TransactionService {

    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(ClientRepository clientRepository, TransactionRepository transactionRepository) {
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void createTransaction(Integer clientId,Integer amount,String type,String description){
        final Client client = clientRepository.findByIdForUpdate(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Cliente nao encontrado para transacionar"));

        final Transaction transaction = new Transaction(amount, TransactionType.valueOf(type), description, client);

        final Integer newBalance = calculateNewBalance(client,transaction);

        clientRepository.updateBalanceById(clientId, newBalance);
        transactionRepository.save(transaction);
    }

    public List<Transaction> listAllByClientId(Integer clientId){
        return transactionRepository.listAllByClientIdOrderByCreatedAtDesc(clientId);
    }
}
