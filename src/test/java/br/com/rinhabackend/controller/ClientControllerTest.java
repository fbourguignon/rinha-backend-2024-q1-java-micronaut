package br.com.rinhabackend.controller;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.hamcrest.CoreMatchers.equalTo;

@MicronautTest
public class ClientControllerTest {

    @Test
    @Order(1)
    @DisplayName("Deve retornar um erro ao informar um tipo de transacao invalido")
    void create_transaction_invalid_type_error(RequestSpecification spec) {
        spec
                .when()
                .body("""
                        {
                           "valor": 1000,
                           "tipo": "f",
                           "descricao": "descricao"
                        }
                        """)
                .contentType(ContentType.JSON)
                .post("/clientes/1/transacoes")
                .then()
                .statusCode(400)
                .body("errors", equalTo(asList("Tipo de transacao invalido")));
    }

    @Test
    @Order(2)
    @DisplayName("Deve retornar um erro ao informar um tipo de transacao invalido")
    void create_transaction_invalid_description(RequestSpecification spec) {
        spec
                .when()
                .body("""
                        {
                           "valor": 1000,
                           "tipo": "d",
                           "descricao": "descricao maior que 10"
                        }
                        """)
                .contentType(ContentType.JSON)
                .post("/clientes/1/transacoes")
                .then()
                .statusCode(400)
                .body("errors", equalTo(asList("O tamanho maximo da descricao e 10 caracteres")));
    }
}
