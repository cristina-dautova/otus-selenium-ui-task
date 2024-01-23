package ru.otus.selenium.factory.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OperaDriverConfiguration implements IBrowserSettings {

    @Override
    public WebDriver configure() {

        WebDriverManager.operadriver().setup();

//        ChromeOptions options = new ChromeOptions();
//        options.setBinary(operaPath);
//        options.addArguments("--start-maximized");
        return new ChromeDriver();
    }
}
