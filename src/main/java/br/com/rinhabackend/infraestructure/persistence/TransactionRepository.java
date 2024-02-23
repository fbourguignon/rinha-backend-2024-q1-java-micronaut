package br.com.rinhabackend.infraestructure.persistence;

import br.com.rinhabackend.domain.model.Transaction;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

//   Transaction save(Account account,
//                    Integer amount,
//                    TransactionType type,
//                    String description,
//                    LocalTime createdAt);

}