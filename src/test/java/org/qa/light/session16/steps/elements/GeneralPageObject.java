package org.qa.light.session16.steps.elements;

import org.openqa.selenium.WebDriver;

public class GeneralPageObject extends AbstractPage {

    public GeneralPageObject(WebDriver driver) {
        super(driver);
    }

    public void clickSomething(ILocatable locatable) {
        takeScreenShot(System.currentTimeMillis() + "-click.png");
        driver.findElement(locatable.getLocator()).click();

    }
}
