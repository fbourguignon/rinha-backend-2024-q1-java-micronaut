package br.com.rinhabackend.infraestructure.persistence;

import br.com.rinhabackend.domain.model.Transaction;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM transaction WHERE client_id = :clientId order by transaction.created_at desc limit 10")
    List<Transaction> listAllByClientIdOrderByCreatedAtDesc(Integer clientId);
}