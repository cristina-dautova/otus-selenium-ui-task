package ru.otus.selenium.factory;

import org.openqa.selenium.WebDriver;
import ru.otus.selenium.applicationconfig.ConfigProvider;
import ru.otus.selenium.exceptions.BrowserNotSupportedException;
import ru.otus.selenium.factory.driver.ChromeDriverConfiguration;
import ru.otus.selenium.factory.driver.FirefoxDriverConfiguration;
import ru.otus.selenium.factory.driver.SelenoidConfiguration;

public class WebDriverFactory implements IFactory<WebDriver> {

  private final String browserName =
      System.getProperty("browser", new ConfigProvider().provide().keySet().iterator().next());

  @Override
  public WebDriver create() {

    switch (browserName) {
      case "chrome":
        return new ChromeDriverConfiguration().configure();
      case "firefox":
        return new FirefoxDriverConfiguration().configure();
      case "selenoid":
        return new SelenoidConfiguration().configure();
      default:
        throw new BrowserNotSupportedException(browserName);
    }
  }
}
