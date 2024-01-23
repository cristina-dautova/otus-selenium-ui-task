package ru.otus.selenium.popups;

public interface IPopup<T> {

    T popupShouldBePresent();
    T popupShouldNotBePresent();
}
