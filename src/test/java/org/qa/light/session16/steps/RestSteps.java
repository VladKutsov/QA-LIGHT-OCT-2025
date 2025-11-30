package org.qa.light.session16.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.qa.light.session10.ResponseDto;

public class RestSteps {

    public static ResponseDto responseDto;

    @Given("I request {int} random people from API")
    public void iRequest3RandomPeopleFromAPI(int amount) {
        responseDto = RestAssured.given()
                .queryParam("inc", "gender,name,nat")
                .queryParam("results", amount)
                .queryParam("noinfo")
                .baseUri("https://randomuser.me/")
                .basePath("/api")
                .get()
                .as(ResponseDto.class);
    }
}
