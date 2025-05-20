package com.bookkeeping.interfaces.rest;

import com.bookkeeping.application.dto.AccountingEntryTypeCreateOrUpdateDTO;
import com.bookkeeping.containers.PostgresTestResource;
import com.bookkeeping.domain.enums.TransactionType;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
class AccountingEntryTypeResourceTest {

    private AccountingEntryTypeCreateOrUpdateDTO createDTO() {
        return new AccountingEntryTypeCreateOrUpdateDTO("RENT", TransactionType.EXPENSE, "Aluguel mensal");
    }

    @Test
    void testCreateAndDeleteEntryType() {
        UUID id = given()
                .contentType(ContentType.JSON)
                .body(createDTO())
                .when()
                .post("/api/v1/accounting-entry-types")
                .then()
                .statusCode(200)
                .body("code", is("RENT"))
                .body("transactionType", is("EXPENSE"))
                .extract().jsonPath().getUUID("id");

        // Verifica se foi criado
        given()
                .when()
                .get("/api/v1/accounting-entry-types/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id.toString()));

        // Deleta
        given()
                .when()
                .delete("/api/v1/accounting-entry-types/{id}", id)
                .then()
                .statusCode(204);

        // Confirma que foi removido
        given()
                .when()
                .get("/api/v1/accounting-entry-types/{id}", id)
                .then()
                .statusCode(404);
    }

    @Test
    void testUpdateEntryType() {
        UUID id = given()
                .contentType(ContentType.JSON)
                .body(createDTO())
                .when()
                .post("/api/v1/accounting-entry-types")
                .then()
                .statusCode(200)
                .extract().jsonPath().getUUID("id");

        var updated = new AccountingEntryTypeCreateOrUpdateDTO("RENT", TransactionType.EXPENSE, "Aluguel atualizado");

        given()
                .contentType(ContentType.JSON)
                .body(updated)
                .when()
                .put("/api/v1/accounting-entry-types/{id}", id)
                .then()
                .statusCode(200)
                .body("description", is("Aluguel atualizado"));

        given()
                .when()
                .delete("/api/v1/accounting-entry-types/{id}", id)
                .then()
                .statusCode(204);
    }

    @Test
    void testGetAllEntryTypes() {
        UUID id = given()
                .contentType(ContentType.JSON)
                .body(createDTO())
                .when()
                .post("/api/v1/accounting-entry-types")
                .then()
                .statusCode(200)
                .extract().jsonPath().getUUID("id");

        given()
                .when()
                .get("/api/v1/accounting-entry-types")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));

        given()
                .when()
                .delete("/api/v1/accounting-entry-types/{id}", id)
                .then()
                .statusCode(204);
    }
}
