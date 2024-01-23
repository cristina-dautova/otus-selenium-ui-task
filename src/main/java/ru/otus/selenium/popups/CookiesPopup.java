package ru.otus.selenium.popups;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.otus.selenium.pages.MainPage;

public class CookiesPopup extends BasePopup {

    public CookiesPopup(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='sc-11pdrud-1']")
    private WebElement cookiesPopup;

    @FindBy(xpath = "//div[@class='sc-11pdrud-3']")
    private WebElement acceptCookiesOkButton;


    public CookiesPopup popupShouldBePresent() {
        wait.until(ExpectedConditions.visibilityOf(cookiesPopup));
        return this;
    }

    public MainPage clickAcceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesOkButton));
        acceptCookiesOkButton.click();
        return new MainPage(driver);
    }
}
