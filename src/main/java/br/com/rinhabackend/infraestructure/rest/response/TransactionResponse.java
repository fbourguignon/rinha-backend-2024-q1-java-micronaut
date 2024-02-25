package br.com.rinhabackend.infraestructure.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;

@Serdeable
@Introspected
public record TransactionResponse(@JsonProperty("valor") Integer integer, @JsonProperty("tipo") String type, @JsonProperty("descricao") String description, @JsonProperty("realizada_em") LocalDateTime createdAt) {
}
