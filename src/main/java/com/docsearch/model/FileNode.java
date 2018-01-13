package com.docsearch.model;

import java.util.Map;

/**
 * File node that holds file name and a word counter
 */
public class FileNode {
    private String fileName;
    private Object details;

    public FileNode(String fileName, Object details) {
        this.fileName = fileName;
        this.details = details;
    }

    public String getFileName() {
        return fileName;
    }

    public Object getDetails() {
        return details;
    }
}
