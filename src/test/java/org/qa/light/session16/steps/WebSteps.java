package org.qa.light.session16.steps;

import io.cucumber.java.en.Given;
import lombok.SneakyThrows;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.qa.light.session15.WebDriverFactory;
import org.qa.light.session16.steps.elements.GeneralPageObject;
import org.qa.light.session16.steps.elements.GooglePageElements;
import org.qa.light.session16.steps.elements.MyWebException;
import org.qa.light.session8.CloudFlarePage;

import java.util.List;
import java.util.Random;

public class WebSteps {
    public static CloudFlarePage cloudFlarePage;
    public static GeneralPageObject generalPageObject;

    @Given("Cloud flare step")
    public void cloudFlareStep() {
        cloudFlarePage.load();
    }

    @SneakyThrows
    @Given("I load google page")
    public void iLoadGooglePage() {
        WebDriverFactory.getDriver().get("https://www.google.com");
    }

    @Given("I accept google cookies")
    public void acceptGoogleCookies() {
        List<WebElement> cookieLink = WebDriverFactory.getDriver().findElements(
                GooglePageElements.COOKIES_LINK.getLocator());
        if (!cookieLink.isEmpty()) {
            WebDriverFactory.getDriver()
                    .findElements(GooglePageElements.ACCEPT_COOKIES.getLocator())
                    .get(5).click();
        }
    }

    @Given("I click {} if present")
    public void iClickIfPresent(GooglePageElements element) {
        List<WebElement> elements = WebDriverFactory.getDriver()
                .findElements(element.getLocator());
        if (!elements.isEmpty()) {
            elements.get(0).click();
        }
    }

    @Given("I click {}")
    public void iClickSearchInput(GooglePageElements element) {
        generalPageObject.clickSomething(element);
    }

    @Given("I set element {} value to {string}")
    public void iSetElementValueTo(GooglePageElements element, String value) {
        WebDriverFactory.getDriver().findElement(element.getLocator()).sendKeys(value);
    }

    @Given("I send {} to {}")
    public void iSendEnterToSearchInput(Keys keys, GooglePageElements element) {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new MyWebException(WebDriverFactory.getDriver(), "Oops!");
        }
        WebDriverFactory.getDriver()
                .findElement(element.getLocator()).sendKeys(keys);
    }
}
