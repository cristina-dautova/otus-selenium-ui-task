package ru.otus.selenium.listeners;

import lombok.SneakyThrows;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class HighlightListener implements WebDriverListener {

  private final WebDriver driver;
  private final int highlightDuration;

  public HighlightListener(WebDriver driver, int highlightDuration) {
    this.driver = driver;
    this.highlightDuration = highlightDuration;
  }

  @Override
  public void beforeClick(WebElement element) {
    highlightElement(element);
  }

  @SneakyThrows
  private void highlightElement(WebElement element) {

    if (driver instanceof JavascriptExecutor) {

      var js = (JavascriptExecutor) driver;

      var originalStyle = element.getAttribute("style");

      js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; background: purple;');",
          element);
      Thread.sleep(highlightDuration);
      js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
    }
  }
}
