package com.docsearch;

import com.docsearch.model.ResultSetNode;

import java.util.Queue;

public interface Search {
    void setup(String[] files);

    Queue<ResultSetNode> search(String token);
}
