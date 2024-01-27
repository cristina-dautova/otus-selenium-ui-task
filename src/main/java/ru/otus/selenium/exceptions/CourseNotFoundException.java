package ru.otus.selenium.exceptions;

import java.util.NoSuchElementException;

public class CourseNotFoundException extends NoSuchElementException {

  public CourseNotFoundException(String courseName) {
    super(String.format("Course with name '%s' is not found.", courseName));
  }
}
