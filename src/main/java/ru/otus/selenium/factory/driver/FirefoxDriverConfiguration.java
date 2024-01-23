package ru.otus.selenium.factory.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverConfiguration implements IBrowserSettings {

    @Override
    public WebDriver configure() {

        WebDriverManager.firefoxdriver().setup();

        var firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--kiosk");
        firefoxOptions.addArguments("--homepage=about:blank");

        return new FirefoxDriver(firefoxOptions);
    }
}
