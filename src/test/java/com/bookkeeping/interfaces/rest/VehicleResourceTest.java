package com.bookkeeping.interfaces.rest;

import com.bookkeeping.application.dto.VehicleCreateOrUpdateDTO;
import com.bookkeeping.containers.PostgresTestResource;
import com.bookkeeping.domain.enums.VehicleStatus;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
class VehicleResourceTest {

    private VehicleCreateOrUpdateDTO createDTO() {
        return new VehicleCreateOrUpdateDTO(
                "ABC1234",
                "Onix",
                "Chevrolet",
                "CHASSIS001",
                2020,
                LocalDate.now(),
                new BigDecimal("55000.00"),
                null, null, null, null,
                "Preto",
                "1.0 Turbo",
                "Gasolina",
                VehicleStatus.AVAILABLE
        );
    }

    @Test
    void testCreateAndDeleteVehicle() {
        UUID id = given()
                .contentType(ContentType.JSON)
                .body(createDTO())
                .when()
                .post("/api/v1/vehicles")
                .then()
                .statusCode(200)
                .body("licensePlate", is("ABC1234"))
                .body("model", is("Onix"))
                .extract().jsonPath().getUUID("id");

        // Verifica se foi criado
        given()
                .when()
                .get("/api/v1/vehicles/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id.toString()));

        // Deleta
        given()
                .when()
                .delete("/api/v1/vehicles/{id}", id)
                .then()
                .statusCode(204);

        // Confirma que foi removido
        given()
                .when()
                .get("/api/v1/vehicles/{id}", id)
                .then()
                .statusCode(404);
    }

    @Test
    void testUpdateVehicle() {
        // Cria
        UUID id = given()
                .contentType(ContentType.JSON)
                .body(createDTO())
                .when()
                .post("/api/v1/vehicles")
                .then()
                .statusCode(200)
                .extract().jsonPath().getUUID("id");

        // Atualiza
        var updated = new VehicleCreateOrUpdateDTO(
                "XYZ9999",
                "Onix Plus",
                "Chevrolet",
                "CHASSIS001",
                2021,
                LocalDate.now(),
                new BigDecimal("62000.00"),
                null, null, null, null,
                "Branco",
                "1.2 Turbo",
                "Flex",
                VehicleStatus.AVAILABLE
        );

        given()
                .contentType(ContentType.JSON)
                .body(updated)
                .when()
                .put("/api/v1/vehicles/{id}", id)
                .then()
                .statusCode(200)
                .body("licensePlate", is("XYZ9999"))
                .body("model", is("Onix Plus"));

        // Limpa
        given()
                .when()
                .delete("/api/v1/vehicles/{id}", id)
                .then()
                .statusCode(204);
    }

    @Test
    void testGetAllVehicles() {
        UUID id = given()
                .contentType(ContentType.JSON)
                .body(createDTO())
                .when()
                .post("/api/v1/vehicles")
                .then()
                .statusCode(200)
                .extract().jsonPath().getUUID("id");

        given()
                .when()
                .get("/api/v1/vehicles")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));

        given()
                .when()
                .delete("/api/v1/vehicles/{id}", id)
                .then()
                .statusCode(204);
    }
}
