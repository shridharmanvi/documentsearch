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
}
