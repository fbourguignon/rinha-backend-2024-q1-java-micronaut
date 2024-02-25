package br.com.rinhabackend.infraestructure.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;
@Serdeable
@Introspected
public record ExtractResponse(@JsonProperty("saldo") BalanceResponse balanceResponse, @JsonProperty("ultimas_transacoes") List<TransactionResponse> transactions) {
}
