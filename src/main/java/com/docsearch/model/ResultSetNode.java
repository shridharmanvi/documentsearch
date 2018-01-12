package com.docsearch.model;

/**
 * Container class to hold filename and number of
 * occurances of the query token within the file
 */
public class ResultSetNode {
    private String filename;
    private int count;

    public ResultSetNode(String filename, int count) {
        this.filename = filename;
        this.count = count;
    }

    public String getFilename() {
        return filename;
    }

    public int getCount() {
        return count;
    }
}
