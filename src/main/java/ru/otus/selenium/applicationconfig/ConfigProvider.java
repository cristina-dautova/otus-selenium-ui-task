package ru.otus.selenium.applicationconfig;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import ru.otus.selenium.applicationconfig.models.Config;
import ru.otus.selenium.applicationconfig.models.EnvironmentConfig;

public class ConfigProvider {

    public EnvironmentConfig provide() {
        var yaml = new Yaml(new Constructor(Config.class, new LoaderOptions()));
        try (var inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config.yml");) {

            Config config = yaml.load(inputStream);

            return config.getEnvironments()
                    .stream()
                    .filter(env -> env.getEnv().equals(config.getActiveEnvironment()))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
