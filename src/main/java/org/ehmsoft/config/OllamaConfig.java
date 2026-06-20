package org.ehmsoft.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OllamaConfig {

    private final Properties properties;

    public OllamaConfig() {

        properties = new Properties();

        try (InputStream input =
                     getClass()
                             .getClassLoader()
                             .getResourceAsStream(
                                     "application.properties"
                             )) {

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Error cargando configuración",
                    e
            );
        }
    }

    public String getUrl() {
        return properties.getProperty("ollama.url");
    }

    public String getModel() {
        return properties.getProperty("ollama.model");
    }
}