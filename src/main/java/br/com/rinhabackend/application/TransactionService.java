package br.com.rinhabackend.application;

import br.com.rinhabackend.domain.exception.ClientNotFoundException;
import br.com.rinhabackend.domain.model.Client;
import br.com.rinhabackend.domain.model.Transaction;
import br.com.rinhabackend.domain.model.TransactionType;
import br.com.rinhabackend.infraestructure.persistence.ClientRepository;
import br.com.rinhabackend.infraestructure.persistence.TransactionRepository;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.time.LocalTime;

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
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Cliente nao encontrado para transacionar"));

        transactionRepository.save(new Transaction(null, amount, TransactionType.D, description, LocalTime.now(), client));

    }
}
