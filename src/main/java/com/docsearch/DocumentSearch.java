package com.docsearch;

import com.docsearch.model.ResultSetNode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Queue;

public interface DocumentSearch {
    void setup(String[] files) throws IOException;

    void setAndValidateToken(String token);

    Queue<ResultSetNode> search(String token);
}
