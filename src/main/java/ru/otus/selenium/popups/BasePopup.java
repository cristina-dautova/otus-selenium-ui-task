package ru.otus.selenium.popups;

import org.openqa.selenium.WebDriver;
import utils.BaseUtils;

public class BasePopup<T> extends BaseUtils implements IPopup<T> {

  public BasePopup(WebDriver driver) {
    super(driver);
  }

  @Override
  public T popupShouldBePresent() {
    return null;
  }

  @Override
  public T popupShouldNotBePresent() {
    return null;
  }
}
