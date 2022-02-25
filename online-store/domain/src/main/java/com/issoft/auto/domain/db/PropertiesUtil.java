package com.issoft.auto.domain.db;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {

        try {
            FileReader reader = new FileReader("domain/src/main/resources/config.properties");
            PROPERTIES.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
