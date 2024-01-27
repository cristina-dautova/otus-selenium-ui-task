package utils;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseUtils {

  protected WebDriver driver;
  protected Actions actions;
  protected WebDriverWait wait;
  protected SoftAssertions softAssertions;

  public BaseUtils(WebDriver driver) {
    this.driver = driver;
    this.actions = new Actions(driver);
    PageFactory.initElements(driver, this);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    this.softAssertions = new SoftAssertions();
  }
}
