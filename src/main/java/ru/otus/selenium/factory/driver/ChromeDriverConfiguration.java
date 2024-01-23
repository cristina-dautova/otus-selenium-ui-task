package ru.otus.selenium.factory.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverConfiguration implements IBrowserSettings {

    private final String browserVersion = System.getProperty("browser.version");

    @Override
    public WebDriver configure() {

        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.addArguments("--homepage=about:blank");

        WebDriverManager.chromedriver().browserVersion(browserVersion).setup();


        return new ChromeDriver(chromeOptions);
    }
}
