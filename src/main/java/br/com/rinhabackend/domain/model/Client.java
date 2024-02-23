package br.com.rinhabackend.domain.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;

import java.util.Set;

@Introspected
@MappedEntity
public record Client(@Id Integer id, String name, Integer limit, Integer balance, @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "account") Set<Transaction> transactions) {
}
