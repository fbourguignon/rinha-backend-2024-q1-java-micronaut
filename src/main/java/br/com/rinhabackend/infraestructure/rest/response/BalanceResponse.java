package br.com.rinhabackend.infraestructure.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;

@Serdeable
@Introspected
public record BalanceResponse(@JsonProperty("total") Integer total, @JsonProperty("data_extrato") LocalDateTime extractDate, @JsonProperty("limite") Integer limit) {
}

