package ru.otus.selenium.factory.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OperaDriverConfiguration implements IBrowserSettings {

  //**
  // TODO */

  @Override
  public WebDriver configure() {

    WebDriverManager.operadriver().setup();

    ChromeOptions options = new ChromeOptions();
    options.setBinary("pathToDriver");
    options.addArguments("--start-maximized");

    return new ChromeDriver();
  }
}
