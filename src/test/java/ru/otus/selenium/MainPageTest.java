package ru.otus.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import ru.otus.selenium.annotations.Driver;
import ru.otus.selenium.annotations.Page;
import ru.otus.selenium.extensions.DriverManagerExtension;
import ru.otus.selenium.pages.MainPage;

@ExtendWith({DriverManagerExtension.class})
public class MainPageTest {

    @Driver
    private WebDriver driver;
    @Page
    private MainPage mainPage;

    @Test
    public void filterCourseByName() {

        var courseName = "Android";

         mainPage
                .open()
                .searchCourseByNameAndClick(courseName)
                .isSpecificLessonsPageDisplayed(courseName)
                .goBack();
    }

    @Test
    public void filterCourseByDate() {

         mainPage
                .open()
                .searchLatestCourseAndClick(false)
                .isLessonsPageDisplayed()
                .goBack();
    }
}
