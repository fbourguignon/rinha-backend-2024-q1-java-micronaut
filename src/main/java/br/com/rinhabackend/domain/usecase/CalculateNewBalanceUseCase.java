package br.com.rinhabackend.domain.usecase;

import br.com.rinhabackend.domain.exception.LimitExceededException;
import br.com.rinhabackend.domain.model.Client;
import br.com.rinhabackend.domain.model.Transaction;
import br.com.rinhabackend.domain.model.TransactionType;

public class CalculateNewBalanceUseCase {

    public static Integer calculateNewBalance(Client client, Transaction transaction){
        final Integer balance = client.balance();
        final Integer limite = client.limit();
        final Integer amount = transaction.amount();
        final Integer newBalance = transaction.type() == TransactionType.C ? balance + amount: balance - amount;

        if(newBalance > limite) {
          throw new LimitExceededException("Limite insuficiente");
        }

        return newBalance;
    }
}
