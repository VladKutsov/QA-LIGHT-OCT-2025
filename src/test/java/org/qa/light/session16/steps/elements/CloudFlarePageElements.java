package org.qa.light.session16.steps.elements;

import lombok.Getter;
import org.openqa.selenium.By;

public enum CloudFlarePageElements implements ILocatable {
    LOGIN(By.name("login"));

    @Getter
    private final By locator;

    CloudFlarePageElements(By locator) {
        this.locator = locator;
    }
}
