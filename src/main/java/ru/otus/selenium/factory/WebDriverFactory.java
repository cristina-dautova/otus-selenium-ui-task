package ru.otus.selenium.factory;

import org.openqa.selenium.WebDriver;
import ru.otus.selenium.exceptions.BrowserNotSupportedException;
import ru.otus.selenium.factory.driver.ChromeDriverConfiguration;
import ru.otus.selenium.factory.driver.FirefoxDriverConfiguration;
import ru.otus.selenium.factory.driver.OperaDriverConfiguration;

public class WebDriverFactory implements IFactory<WebDriver> {

    private final String browserName = System.getProperty("browser", "chrome");

    @Override
    public WebDriver create() {

        return switch (browserName) {
            case "chrome" -> new ChromeDriverConfiguration().configure();
            case "firefox" -> new FirefoxDriverConfiguration().configure();
            case "opera" -> new OperaDriverConfiguration().configure();
            default -> throw new BrowserNotSupportedException(browserName);
        };
    }
}
