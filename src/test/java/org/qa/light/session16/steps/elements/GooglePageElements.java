package org.qa.light.session16.steps.elements;

import org.openqa.selenium.By;

public enum GooglePageElements implements ILocatable {
    COOKIES_LINK(By.xpath("//a[contains(@href, 'technologies/cookies')]")),
    SEARCH_INPUT(By.name("q")),
    ACCEPT_COOKIES(By.tagName("button"));

    private final By locator;

    GooglePageElements(By locator) {
        this.locator = locator;
    }

    @Override
    public By getLocator() {
        return locator;
    }
}
