package com.bookkeeping.interfaces.rest;

import com.bookkeeping.application.dto.AccountingEntryLabelCreateOrUpdateDTO;
import com.bookkeeping.containers.PostgresTestResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
class AccountingEntryLabelResourceTest {

    private AccountingEntryLabelCreateOrUpdateDTO createDTO() {
        return new AccountingEntryLabelCreateOrUpdateDTO("RENT", "Aluguel mensal");
    }

    @Test
    void testCreateAndDeleteLabel() {
        UUID id = given()
                .contentType(ContentType.JSON)
                .body(createDTO())
                .when()
                .post("/api/v1/accounting-entry-labels")
                .then()
                .statusCode(200)
                .body("code", is("RENT"))
                .body("description", is("Aluguel mensal"))
                .extract().jsonPath().getUUID("id");

        // Verifica se foi criado
        given()
                .when()
                .get("/api/v1/accounting-entry-labels/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id.toString()));

        // Deleta
        given()
                .when()
                .delete("/api/v1/accounting-entry-labels/{id}", id)
                .then()
                .statusCode(204);

        // Confirma que foi removido
        given()
                .when()
                .get("/api/v1/accounting-entry-labels/{id}", id)
                .then()
                .statusCode(404);
    }

    @Test
    void testUpdateLabel() {
        // Cria
        UUID id = given()
                .contentType(ContentType.JSON)
                .body(createDTO())
                .when()
                .post("/api/v1/accounting-entry-labels")
                .then()
                .statusCode(200)
                .extract().jsonPath().getUUID("id");

        // Atualiza
        var updated = new AccountingEntryLabelCreateOrUpdateDTO("RENT", "Aluguel atualizado");

        given()
                .contentType(ContentType.JSON)
                .body(updated)
                .when()
                .put("/api/v1/accounting-entry-labels/{id}", id)
                .then()
                .statusCode(200)
                .body("description", is("Aluguel atualizado"));

        // Limpa
        given()
                .when()
                .delete("/api/v1/accounting-entry-labels/{id}", id)
                .then()
                .statusCode(204);
    }

    @Test
    void testGetAllLabels() {
        UUID id = given()
                .contentType(ContentType.JSON)
                .body(createDTO())
                .when()
                .post("/api/v1/accounting-entry-labels")
                .then()
                .statusCode(200)
                .extract().jsonPath().getUUID("id");

        given()
                .when()
                .get("/api/v1/accounting-entry-labels")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));

        given()
                .when()
                .delete("/api/v1/accounting-entry-labels/{id}", id)
                .then()
                .statusCode(204);
    }
}
