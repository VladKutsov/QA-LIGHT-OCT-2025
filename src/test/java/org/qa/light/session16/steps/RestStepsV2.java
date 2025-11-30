package org.qa.light.session16.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.qa.light.session10.ResponseDto;
import org.qa.light.session12.DataHoler;

public class RestStepsV2 {

    @Given("I request {int} random people from API as {string}")
    public void iRequest3RandomPeopleFromAPI(int amount, String alias) {
        DataHoler.DATA.put(alias, RestAssured.given()
                .queryParam("inc", "gender,name,nat")
                .queryParam("results", amount)
                .queryParam("noinfo")
                .baseUri("https://randomuser.me/")
                .basePath("/api")
                .get()
                .as(ResponseDto.class));
    }
}
