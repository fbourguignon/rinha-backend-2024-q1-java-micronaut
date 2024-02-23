package br.com.rinhabackend.controller;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;


import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.hamcrest.CoreMatchers.equalTo;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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


    @Test
    @Order(3)
    @DisplayName("Deve retornar ao tentar criar uma transacao com um client que nao existe")
    void create_transaction_account_not_exists(RequestSpecification spec) {
        spec
                .when()
                .body("""
                        {
                           "valor": 1000,
                           "tipo": "d",
                           "descricao": "descricao"
                        }
                        """)
                .contentType(ContentType.JSON)
                .post("/clientes/6/transacoes")
                .then()
                .statusCode(404)
                .body( equalTo("Cliente nao encontrado para transacionar"));
    }

    @Test
    @Order(4)
    @DisplayName("Deve retornar sucesso ao criar uma transacoes")
    void create_transaction_with_valid_data(RequestSpecification spec) {
        spec
                .when()
                .body("""
                        {
                           "valor": 1000,
                           "tipo": "d",
                           "descricao": "transacao1"
                        }
                        """)
                .contentType(ContentType.JSON)
                .post("/clientes/5/transacoes")
                .then()
                .statusCode(200)
                .body("limite", equalTo(500000),
                "saldo",equalTo(-1000));

        spec
                .when()
                .body("""
                        {
                           "valor": 1000,
                           "tipo": "d",
                           "descricao": "transacao2"
                        }
                        """)
                .contentType(ContentType.JSON)
                .post("/clientes/5/transacoes")
                .then()
                .statusCode(200)
                .body("limite", equalTo(500000),
                        "saldo",equalTo(-2000));

        spec
                .when()
                .body("""
                        {
                           "valor": 1500,
                           "tipo": "c",
                           "descricao": "transacao3"
                        }
                        """)
                .contentType(ContentType.JSON)
                .post("/clientes/5/transacoes")
                .then()
                .statusCode(200)
                .body("limite", equalTo(500000),
                        "saldo",equalTo(-500));
    }
}
