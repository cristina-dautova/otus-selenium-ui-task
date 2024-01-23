package ru.otus.selenium.applicationconfig.models;

import lombok.Data;

@Data
public class EnvironmentConfig {

    private String env;
    private String baseUrl;
}
