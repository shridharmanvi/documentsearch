package com.docsearch.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigLoader.class);
    private static Properties config;
    private static final String CONFIG_PROPERTY = "config.file";
    private static final String CONFIG_PROPERTY_DEFAULT_VALUE = "config.properties";

    public static Properties getConfig() {
        if (config == null) {
            config = new Properties();
            setPropertiesFileLocation();
            readConfig();
        }
        return config;
    }

    public static void clearConfig() {
        config = null;
    }

    private static void setPropertiesFileLocation() {
        String propertiesFile = System.getProperty(CONFIG_PROPERTY);
        if (propertiesFile == null || propertiesFile.length() == 0) {
            System.setProperty(CONFIG_PROPERTY, CONFIG_PROPERTY_DEFAULT_VALUE);
        } else {
            System.setProperty(CONFIG_PROPERTY, propertiesFile);
        }
    }

    private static Properties readConfig() {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(System.getProperty(CONFIG_PROPERTY));
        } catch (FileNotFoundException e) {
            inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(System.getProperty(CONFIG_PROPERTY));
        }
        try {
            config.load(inputStream);
            LOG.info("Loaded config from properties file.");
        } catch (IOException e) {
            LOG.error("Error loading properties. Exiting...", e);
            throw new RuntimeException("Cannot load properties file.");
        }
        return config;
    }
}
