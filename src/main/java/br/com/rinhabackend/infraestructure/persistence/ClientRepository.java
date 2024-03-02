package br.com.rinhabackend.infraestructure.persistence;

import br.com.rinhabackend.domain.model.Client;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface ClientRepository extends CrudRepository<Client, Integer> {

    Optional<Client> findByIdForUpdate(Integer id);

    void updateBalanceById(Integer id, Integer balance);

    @Query(nativeQuery = true, value = """
    SELECT *, 
    transaction.id as transactions_id,
    transaction.amount as transactions_amount,
    transaction.type as transactions_type,
    transaction.description as transactions_description,
    transaction.created_at as transactions_created_at,
    transaction.client_id as transactions_client_id
    FROM client client LEFT JOIN transaction transaction ON transaction.client_id = client.id WHERE client.id = :id order by transaction.created_at desc limit 10""")
    @Join(value = "transactions")
    Optional<Client> findAccountWithTransactionById(Integer id);

}

