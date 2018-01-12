package com.docsearch.model;

import java.util.Map;

/**
 * File node that holds file name and a word counter
 */
public class FileNode {
    private String fileName;
    private Map<String, Integer> counter;

    public FileNode(String fileName, Map<String, Integer> counter) {
        this.fileName = fileName;
        this.counter = counter;
    }

    public String getFileName() {
        return fileName;
    }

    public Map<String, Integer> getCounter() {
        return counter;
    }

}
