package ru.otus.selenium.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.otus.selenium.exceptions.CourseNotFoundException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MainPage extends BasePage<MainPage> {

  public MainPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//div[contains(@class, 'sc-1ftuaec-3')]")
  private List<WebElement> courses;

  @FindBy(xpath = "//span[starts-with(text(),'С ')]")
  private List<WebElement> calendarElements;

  public LessonsPage searchLatestCourseAndClick(Boolean isLatest) {
    var chosenCourse = isLatest ? searchLatestCourse() : searchEarliestCourse();
    waitMoveToElementAndClick(chosenCourse);
    return new LessonsPage(driver);
  }

  public LessonsPage searchCourseByNameAndClick(String courseName) {
    var chosenCourse = searchCourseByName(courseName);
    waitMoveToElementAndClick(chosenCourse);
    return new LessonsPage(driver);
  }

  private void waitMoveToElementAndClick(WebElement chosenCourse) {
    if (chosenCourse != null) {
      wait.until(ExpectedConditions.elementToBeClickable(chosenCourse));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", chosenCourse);
      actions.moveToElement(chosenCourse).perform();
      chosenCourse.click();
    }
  }

  private WebElement searchCourseByName(String courseName) {

    wait.until(ExpectedConditions.visibilityOfAllElements(courses));

    return courses.stream()
        .map(course -> course.findElement(By.xpath(".//h5")))
        .toList()
        .stream()
        .filter(childNode -> childNode.getText().contains(courseName))
        .findFirst()
        .orElseThrow(() -> new CourseNotFoundException(courseName));
  }

  private WebElement searchEarliestCourse() {
    return getDateWebElementEntryStream()
        .reduce((entry1, entry2) -> entry1.getKey().isBefore(entry2.getKey()) ? entry1 : entry2)
        .orElseThrow()
        .getValue();
  }

  private WebElement searchLatestCourse() {
    return getDateWebElementEntryStream()
        .reduce((entry1, entry2) -> entry1.getKey().isAfter(entry2.getKey()) ? entry1 : entry2)
        .orElseThrow()
        .getValue();
  }

  private Stream<Map.Entry<LocalDate, WebElement>> getDateWebElementEntryStream() {

    var regex = "^С (\\d{1,2} [а-я]+)";
    var pattern = Pattern.compile(regex);
    var dateFormat = new SimpleDateFormat("dd MMMM", new Locale("ru"));

    return calendarElements
        .stream()
        .map(calendarWebElement -> {
          var matcher = pattern.matcher(calendarWebElement.getText());
          return (matcher.find()) ? Map.entry(matcher.group(1), calendarWebElement) : null;
        })
        .map(webElementEntry -> parseDate(webElementEntry, dateFormat));
  }

  @SneakyThrows
  private Map.Entry<LocalDate, WebElement> parseDate(Map.Entry<String, WebElement> entry,
                                                     SimpleDateFormat dateFormat) {
    LocalDate localDate = dateFormat.parse(entry.getKey())
        .toInstant()
        .atZone(dateFormat.getTimeZone().toZoneId())
        .toLocalDate();
    return Map.entry(localDate, entry.getValue());
  }
}
