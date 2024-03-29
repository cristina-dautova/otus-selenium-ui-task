package ru.otus.selenium.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.otus.selenium.applicationconfig.ConfigProvider;
import utils.BaseUtils;

import java.util.List;

public abstract class BasePage<T> extends BaseUtils {

  public BasePage(WebDriver driver) {
    super(driver);
  }

  public T open() {
    var envConfig = new ConfigProvider().provide().values().iterator().next();
    driver.get(envConfig.getBaseUrl());
    return (T) this;
  }

  @SneakyThrows
  boolean isElementFound(By by, int timeout) {
    List<WebElement> elements = driver.findElements(by);
    for (int i = 0; (i < timeout) && (elements.isEmpty()); i++) {
      Thread.sleep(1000);
      elements = driver.findElements(by);
    }
    return !elements.isEmpty();
  }
}
