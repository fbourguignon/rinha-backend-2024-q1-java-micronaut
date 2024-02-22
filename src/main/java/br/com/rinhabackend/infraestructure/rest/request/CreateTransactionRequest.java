package br.com.rinhabackend.infraestructure.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.*;

@Introspected
@Serdeable
public record CreateTransactionRequest(
        @JsonProperty("valor") @NotNull(message = "Informe o valor da transacao") Integer value,
        @JsonProperty("tipo") @NotNull(message = "Informe o tipo da transacao") @Pattern(regexp = "^[dc]$", message = "Tipo de transacao invalido") String type,
        @JsonProperty("descricao") @NotEmpty(message = "Informe a descricao")  @Size(max = 10, message = "O tamanho maximo da descricao e 10 caracteres") String description) {

}
