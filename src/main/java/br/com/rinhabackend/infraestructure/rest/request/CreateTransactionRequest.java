package br.com.rinhabackend.infraestructure.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Introspected
@Serdeable
public record CreateTransactionRequest(
        @JsonProperty("valor") @Digits(integer = 20, fraction = 0, message = "O campo deve ser um numero inteiro sem casas decimais") BigDecimal value,
        @JsonProperty("tipo") @NotNull(message = "Informe o tipo da transacao") @Pattern(regexp = "^[dc]$", message = "Tipo de transacao invalido") String type,
        @JsonProperty("descricao") @NotEmpty(message = "Informe a descricao")  @Size(max = 10, message = "O tamanho maximo da descricao e 10 caracteres") String description) {

}
