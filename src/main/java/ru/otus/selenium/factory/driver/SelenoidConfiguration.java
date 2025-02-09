package ru.otus.selenium.factory.driver;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SelenoidConfiguration implements IBrowserSettings {

  private final String browserVersion = System.getProperty("browser.version");

  @Override
  @SneakyThrows
  public WebDriver configure() {

    var chromeOptions = new ChromeOptions();

    chromeOptions.setCapability(CapabilityType.BROWSER_VERSION, "121.0");
    Map<String, Object> selenoidOptions = new HashMap<>();
    selenoidOptions.put("enableVNC", true);
    chromeOptions.setCapability("selenoid:options", selenoidOptions);

    return new RemoteWebDriver(
        URI.create("http://127.0.0.1/wd/hub").toURL(),
        chromeOptions
    );
  }
}
