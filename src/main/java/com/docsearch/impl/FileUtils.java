package com.docsearch.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileUtils {

    public BufferedReader readFile(String path) throws FileNotFoundException {
        FileReader fileReader = new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader);
        return br;
    }

    private static void setPropertiesFileLocations() {
        Object p = System.getProperty("plugins.properties");
        if (p == null || ((String) p).length() == 0) {
            System.setProperty("plugins.properties", "classpath:properties/config.properties");
        } else {
            System.setProperty("plugins.properties", "file:" + p);
        }
    }
}
