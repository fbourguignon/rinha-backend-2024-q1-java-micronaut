package br.com.rinhabackend.domain.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;

import java.time.LocalTime;

@Introspected
@MappedEntity
public record Transaction(@Id @GeneratedValue @Nullable Long id, Integer amount, TransactionType type, String description, LocalTime createdAt,  @Relation(Relation.Kind.MANY_TO_ONE) @MappedProperty("client_id") Client client) {
}
