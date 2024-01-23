package ru.otus.selenium.exceptions;

public class BrowserNotSupportedException extends RuntimeException {

    public BrowserNotSupportedException(String browserName) {
        super(String.format("Browser % not supported", browserName));
    }
}
