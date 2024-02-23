package br.com.rinhabackend.infraestructure.persistence;

import br.com.rinhabackend.domain.model.Client;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface ClientRepository extends CrudRepository<Client, Integer> {
}

