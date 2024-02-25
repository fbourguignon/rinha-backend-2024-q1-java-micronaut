package br.com.rinhabackend.usecase;

import br.com.rinhabackend.domain.exception.LimitExceededException;
import br.com.rinhabackend.domain.model.Client;
import br.com.rinhabackend.domain.model.Transaction;
import br.com.rinhabackend.domain.model.TransactionType;
import br.com.rinhabackend.domain.usecase.CalculateNewBalanceUseCase;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class CalculateNewBalanceUseCaseTest {

    @Test
    @DisplayName("Deve lancar uma exception em transacoes que ultrapassem o limite")
    public void testLimitExceededException(){
        assertThrows(LimitExceededException.class, () -> {
            CalculateNewBalanceUseCase.calculateNewBalance(
                    new Client(null, null, 1000, 0,null),
                    new Transaction(1001, TransactionType.d, null, null)
            );
        });

        assertThrows(LimitExceededException.class, () -> {
            CalculateNewBalanceUseCase.calculateNewBalance(
                    new Client(null, null, 1200, -1000,null),
                    new Transaction(201, TransactionType.d, null, null)
            );
        });
    }

    @Test
    @DisplayName("Deve permitir uma transacao igual ao limite e calcular um novo saldo")
    public void testValidExceptionEqualLimite(){
        assertEquals(-1000, CalculateNewBalanceUseCase.calculateNewBalance(
            new Client(null, null, 1000, 0,null),
                new Transaction(1000, TransactionType.d, null, null)
        ));
    }

    @Test
    @DisplayName("Deve calcular uma transacao de credito valida")
    public void testValidDebitTransaction(){
        assertEquals(5000, CalculateNewBalanceUseCase.calculateNewBalance(
                new Client(null, null, 1000, -1000,null),
                new Transaction(6000, TransactionType.c, null, null)
        ));

    }

    @Test
    @DisplayName("Deve calcular uma transacao de debito valida")
    public void testValidCreditTransaction(){
        assertEquals(5000, CalculateNewBalanceUseCase.calculateNewBalance(
                new Client(null, null, 1000, -1000,null),
                new Transaction(6000, TransactionType.c, null, null)
        ));
    }

}
