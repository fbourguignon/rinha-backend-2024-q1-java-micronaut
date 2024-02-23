package br.com.rinhabackend.application;

import br.com.rinhabackend.domain.exception.ClientNotFoundException;
import br.com.rinhabackend.domain.model.Client;
import br.com.rinhabackend.domain.model.Transaction;
import br.com.rinhabackend.domain.model.TransactionType;
import br.com.rinhabackend.infraestructure.persistence.ClientRepository;
import br.com.rinhabackend.infraestructure.persistence.TransactionRepository;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;


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

        Transaction transaction = new Transaction(amount, TransactionType.valueOf(type.toUpperCase()), description, client);

        clientRepository.updateBalanceById(clientId, calculateNewBalance(client,transaction));
        transactionRepository.save(transaction);
    }
}
