package ru.otus.selenium.extensions;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.otus.selenium.annotations.Driver;
import ru.otus.selenium.annotations.Page;
import ru.otus.selenium.factory.WebDriverFactory;
import ru.otus.selenium.listeners.HighlightListener;
import ru.otus.selenium.pages.BasePage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DriverManagerExtension implements BeforeEachCallback, AfterEachCallback {

    private WebDriver driver;

    @Override
    public void afterEach(ExtensionContext context) {
        if (driver != null) {
            driver.close();
            if (driver instanceof ChromeDriver) {
                driver.quit();
            }
        }
    }

    @SneakyThrows
    @Override
    public void beforeEach(ExtensionContext context) {

        driver = new WebDriverFactory().create();
        driver = new EventFiringDecorator<>(new HighlightListener(driver, 500)).decorate(driver);

        getAnnotatedFields(Driver.class, context)
                .stream()
                .filter(field -> field.getType().getName().equals(WebDriver.class.getName()))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(context.getTestInstance().get(), driver);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        getAnnotatedFields(Page.class, context)
                .stream()
                .filter(field -> BasePage.class.isAssignableFrom(field.getType()))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(context.getTestInstance().get(),
                                field.getType().getDeclaredConstructor(WebDriver.class).newInstance(driver));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    private Set<Field> getAnnotatedFields(Class<? extends Annotation> annotation, ExtensionContext extensionContext) {

        Set<Field> fields = new HashSet<>();
        Class<?> testClass = extensionContext.getTestClass().get();
        Arrays.stream(testClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation))
                .forEach(fields::add);

        return fields;
    }
}
