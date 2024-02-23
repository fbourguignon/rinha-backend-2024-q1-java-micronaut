package br.com.rinhabackend.domain.model;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Transaction(Integer value, TransactionType type, String description) {
}
