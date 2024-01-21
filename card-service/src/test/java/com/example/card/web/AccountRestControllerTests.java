package com.example.card.web;

import com.example.card.CardServicesApplicationTests;
import com.example.card.dto.CreateAccountDto;
import com.example.card.dto.UpdateAccountDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.*;

public class AccountRestControllerTests extends CardServicesApplicationTests {
    
    private CreateAccountDto createAccountDto;
    private UpdateAccountDto updateAccountDto;

    @Before
    public void setUp() {
        createAccountDto = new CreateAccountDto();
        createAccountDto.setClientId("63894943737");
        createAccountDto.setIban("KE2110001");
        createAccountDto.setBicSwift("ABCLKENA");

        updateAccountDto = new UpdateAccountDto();
        updateAccountDto.setIban("KE2110002TEST");
        updateAccountDto.setBicSwift("CBAFKENX");
    }

    @Test
    public void createAccountFailsWhenInvalidPayload() {
        CreateAccountDto createAccountDto = new CreateAccountDto();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createAccountDto)
                .post("/accounts")
                .then().log().all()
                .statusCode(400)
                .body(containsString("must not be blank"));
    }

    @Test
    public void createAccountFailsWhenInvalidLengths() {
        createAccountDto.setBicSwift(RandomStringUtils.randomAlphabetic(300));
        createAccountDto.setIban(RandomStringUtils.randomAlphabetic(300));
        createAccountDto.setClientId(RandomStringUtils.randomAlphanumeric(300));

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createAccountDto)
                .post("/accounts")
                .then().log().all()
                .statusCode(400)
                .body("messages", containsInAnyOrder("bic Swift size must be between 0 and 250",
                        "iban size must be between 0 and 250",
                        "client Id size must be between 0 and 250"));
    }

    @Test
    public void createAccountWorks() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createAccountDto).log().all()
                .post("/accounts")
                .then().log().all()
                .statusCode(200)
                .body("accountId", notNullValue())
                .body("bicSwift", equalTo(createAccountDto.getBicSwift()))
                .body("iban", equalTo(createAccountDto.getIban()))
                .body("clientId", equalTo(createAccountDto.getClientId()));
    }

    @Test
    public void updateAccountFailsWhenInvalidPayload() {
        UpdateAccountDto updateAccountDto = new UpdateAccountDto();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateAccountDto)
                .put("/accounts/{accountId}", "12002")
                .then().log().all()
                .statusCode(400)
                .body(containsString("must not be blank"));
    }

    @Test
    public void updateAccountFailsWhenInvalidLengths() {
        updateAccountDto.setBicSwift(RandomStringUtils.randomAlphabetic(300));
        updateAccountDto.setIban(RandomStringUtils.randomAlphabetic(300));

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateAccountDto)
                .put("/accounts/{accountId}", "12002")
                .then().log().all()
                .statusCode(400)
                .body("messages", containsInAnyOrder("bic Swift size must be between 0 and 250",
                        "iban size must be between 0 and 250"));
    }

    @Test
    public void updateAccountWorks() {

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateAccountDto).log().all()
                .put("/accounts/{accountId}", "12002")
                .then().log().all()
                .statusCode(200)
                .body("accountId", equalTo("12002"))
                .body("bicSwift", equalTo(updateAccountDto.getBicSwift()))
                .body("iban", equalTo(updateAccountDto.getIban()));
    }

    @Test
    public void getAccountWorks() {

        RestAssured
                .given()
                .get("/accounts/{accountId}", "12001")
                .then().log().all()
                .statusCode(200)
                .body("accountId", equalTo("12001"))
                .body("bicSwift", equalTo("ABCLKENA"))
                .body("iban", equalTo("KE2110001"))
                .body("clientId", equalTo("61894943737"));
    }


    @Test
    public void getAccountFails() {

        RestAssured
                .given()
                .get("/accounts/{accountId}", "948774798734")
                .then().log().all()
                .statusCode(400)
                .body(containsString("Account does not exist"));
    }

    @Test
    public void getAccountsWorks() {

        RestAssured
                .given()
                .get("/accounts")
                .then().log().all()
                .statusCode(200)
                .body("content.size()", greaterThanOrEqualTo(2));
    }

    @Test
    public void getAccountCardsWorks() {

        RestAssured
                .given()
                .get("/accounts/{accountId}/cards", "12002")
                .then().log().all()
                .statusCode(200)
                .body("content.size()", greaterThanOrEqualTo(2))
                .body("content[0].account.accountId", equalTo("12002"));
    }

    @Test
    public void deleteAccountsWorks() {

        RestAssured
                .given()
                .get("/accounts/{accountId}", "12003")
                .then()
                .statusCode(200);

        RestAssured
                .given()
                .delete("/accounts/{accountId}", "12003")
                .then()
                .statusCode(200);

        RestAssured
                .given()
                .get("/accounts/{accountId}", "12003")
                .then().log().all()
                .statusCode(400)
                .body(containsString("Account does not exist"));

        //associated cards were also deleted
        RestAssured
                .given()
                .get("/cards/{cardId}", "808002")
                .then()
                .statusCode(400)
                .body(containsString("Card not found"));

        RestAssured
                .given()
                .get("/cards/{cardId}", "808003")
                .then()
                .statusCode(400)
                .body(containsString("Card not found"));
    }

}
