package ru.otus.selenium.applicationconfig.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Config {

  @Setter
  @Getter
  private String activeEnvironment;
  private List<EnvironmentConfig> environments;

  public List<EnvironmentConfig> getEnvironments() {
    return new ArrayList<>(environments);
  }

  public void setEnvironments(List<EnvironmentConfig> environments) {
    this.environments = new ArrayList<>(environments);
  }
}
