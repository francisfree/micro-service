package com.example.card.web;

import com.example.card.CardServicesApplicationTests;
import com.example.card.dto.CreateClientDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class ClientRestControllerTests extends CardServicesApplicationTests {
    
    private CreateClientDto createClientDto;

    @Before
    public void setUp() {
        createClientDto = new CreateClientDto();
        createClientDto.setName("Client AA");
    }

    @Test
    public void createClientFailsWhenInvalidPayload() {
        CreateClientDto createClientDto = new CreateClientDto();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createClientDto)
                .post("/clients")
                .then().log().all()
                .statusCode(400)
                .body(containsString("must not be blank"));
    }

    @Test
    public void createClientFailsWhenInvalidLengths() {
        createClientDto.setName(RandomStringUtils.randomAlphabetic(300));

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createClientDto)
                .post("/clients")
                .then().log().all()
                .statusCode(400)
                .body("messages", containsInAnyOrder("name size must be between 0 and 250"));
    }

    @Test
    public void createClientWorks() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createClientDto).log().all()
                .post("/clients")
                .then().log().all()
                .statusCode(200)
                .body("clientId", notNullValue())
                .body("name", equalTo(createClientDto.getName()));
    }

    @Test
    public void getClientWorks() {

        RestAssured
                .given()
                .get("/clients/{clientId}", 12)
                .then().log().all()
                .statusCode(200)
                .body("clientId", equalTo(12))
                .body("name", equalTo("Client B"));
    }


    @Test
    public void getClientFails() {

        RestAssured
                .given()
                .get("/clients/{clientId}", 948774798734L)
                .then().log().all()
                .statusCode(400)
                .body(containsString("Client not found"));
    }

    @Test
    public void getClientsWorks() {

        RestAssured
                .given()
                .get("/clients")
                .then().log().all()
                .statusCode(200)
                .body("content.size()", greaterThanOrEqualTo(2));
    }

}
