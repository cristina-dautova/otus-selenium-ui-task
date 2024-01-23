package ru.otus.selenium.pages;

import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class LessonsPage extends BasePage<LessonsPage> {

    public LessonsPage(WebDriver driver) {
        super(driver);
    }

    public MainPage goBack() {
        driver.navigate().back();
        return new MainPage(driver);
    }

    public LessonsPage isLessonsPageDisplayed() {
        assertThat(driver.getCurrentUrl())
                .describedAs("Lessons page URL comparison")
                .contains("/lessons/");
        return this;
    }

    public LessonsPage isSpecificLessonsPageDisplayed(String courseName) {
        softAssertions.assertThat(driver.getCurrentUrl())
                .as("Lessons page URL comparison")
                .contains("/lessons/");
        softAssertions.assertThat(driver.getTitle())
                .as("Page title has no " + courseName + " keyword")
                .containsIgnoringCase(courseName);
        softAssertions.assertAll();
        return this;
    }
}
