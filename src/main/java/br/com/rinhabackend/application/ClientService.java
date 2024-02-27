package br.com.rinhabackend.application;

import br.com.rinhabackend.domain.exception.ClientNotFoundException;
import br.com.rinhabackend.domain.model.Client;
import br.com.rinhabackend.infraestructure.persistence.ClientRepository;
import jakarta.inject.Singleton;

@Singleton
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client getClientById(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente nao encontrado para exibir o extrato"));
    }
}
