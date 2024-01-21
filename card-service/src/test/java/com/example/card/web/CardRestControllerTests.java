package com.example.card.web;

import com.example.card.CardServicesApplicationTests;
import com.example.card.datatypes.CardType;
import com.example.card.dto.CreateCardDto;
import com.example.card.dto.UpdateCardDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class CardRestControllerTests extends CardServicesApplicationTests {
    
    private CreateCardDto createCardDto;
    private UpdateCardDto updateCardDto;

    @Before
    public void setUp() {
        createCardDto = new CreateCardDto();
        createCardDto.setType(CardType.Virtual);
        createCardDto.setAlias("John Doe");
        createCardDto.setAccountId("12001");

        updateCardDto = new UpdateCardDto();
        updateCardDto.setAlias("Jane Doe");
    }

    @Test
    public void createCardFailsWhenInvalidPayload() {
        CreateCardDto createCardDto = new CreateCardDto();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createCardDto)
                .post("/cards")
                .then().log().all()
                .statusCode(400)
                .body(containsString("must not be blank"));
    }

    @Test
    public void createCardFailsWhenInvalidLengths() {
        createCardDto.setAlias(RandomStringUtils.randomAlphabetic(300));
        createCardDto.setAccountId(RandomStringUtils.randomAlphabetic(300));

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createCardDto)
                .post("/cards")
                .then().log().all()
                .statusCode(400)
                .body("messages", containsInAnyOrder("alias size must be between 0 and 250",
                        "account Id size must be between 0 and 250"));
    }

    @Test
    public void createCardWorks() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createCardDto).log().all()
                .post("/cards")
                .then().log().all()
                .statusCode(200)
                .body("cardId", notNullValue())
                .body("type", equalTo(createCardDto.getType().name()))
                .body("alias", equalTo(createCardDto.getAlias()))
                .body("account.accountId", equalTo(createCardDto.getAccountId()));
    }

    @Test
    public void updateCardFailsWhenInvalidPayload() {
        UpdateCardDto updateCardDto = new UpdateCardDto();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateCardDto)
                .put("/cards/{accountId}", "808002")
                .then().log().all()
                .statusCode(400)
                .body(containsString("must not be blank"));
    }

    @Test
    public void updateCardFailsWhenInvalidLengths() {
        updateCardDto.setAlias(RandomStringUtils.randomAlphabetic(300));

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateCardDto)
                .put("/cards/{accountId}", "808002")
                .then().log().all()
                .statusCode(400)
                .body("messages", containsInAnyOrder("alias size must be between 0 and 250"));
    }

    @Test
    public void updateCardWorks() {

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateCardDto).log().all()
                .put("/cards/{accountId}", "808005")
                .then().log().all()
                .statusCode(200)
                .body("cardId", equalTo("808005"))
                .body("type", equalTo(CardType.Physical.name()))
                .body("alias", equalTo(updateCardDto.getAlias()));
    }

    @Test
    public void getCardWorks() {

        RestAssured
                .given()
                .get("/cards/{cardId}", "808001")
                .then().log().all()
                .statusCode(200)
                .body("cardId", equalTo("808001"))
                .body("type", equalTo(CardType.Virtual.name()))
                .body("alias", equalTo("Ken Doe"))
                .body("account.accountId", equalTo("12001"));
    }

    @Test
    public void getCardFails() {

        RestAssured
                .given()
                .get("/cards/{cardId}", "98743151")
                .then().log().all()
                .statusCode(400)
                .body(containsString("Card not found"));
    }

    @Test
    public void getCardsWorks() {

        RestAssured
                .given()
                .get("/cards")
                .then().log().all()
                .statusCode(200)
                .body("content.size()", greaterThanOrEqualTo(2));
    }

    @Test
    public void deleteCardsWorks() {

        RestAssured
                .given()
                .get("/cards/{cardId}", "808004")
                .then()
                .statusCode(200);

        RestAssured
                .given()
                .delete("/cards/{cardId}", "808004")
                .then().log().all()
                .statusCode(200);

        RestAssured
                .given()
                .get("/cards/{cardId}", "808004")
                .then().log().all()
                .statusCode(400)
                .body(containsString("Card not found"));
    }

}
