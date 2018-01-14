package com.docsearch.impl;

import com.docsearch.DocumentSearch;
import com.docsearch.model.FileNode;
import com.docsearch.model.ResultSetNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;


/**
 * This class implements a base version of search. Common tasks like reading
 * a file etc are done here to avoid duplication
 */
public class BaseDocumentSearchImpl implements DocumentSearch {
    protected FileUtils fileUtils;
    protected LinkedList<FileNode> fileNodes;
    protected Queue<ResultSetNode> queue;
    protected String token;

    public BaseDocumentSearchImpl() {
        token = null;
        fileUtils = new FileUtils();
        fileNodes = new LinkedList<>();
        queue = new PriorityQueue<>((ResultSetNode a, ResultSetNode b) -> b.getCount() - a.getCount());
    }

    /**
     * Base version caches lines into a list.
     * Override for a different behaviour
     *
     * @param files
     */
    @Override
    public void setup(String[] files) throws IOException {
        for (String fileName : files) {
            String line;

            BufferedReader bufferedReader = fileUtils.readFile(fileName);
            List<String> lines = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            FileNode fileNode = new FileNode(fileName, lines);
            fileNodes.add(fileNode);
        }
    }

    @Override
    public void setAndValidateToken(String token) {
        this.token= token;
        if (token.isEmpty()) {
            throw new RuntimeException("Invalid token entered! Exiting..");
        }
    }

    @Override
    public Queue<ResultSetNode> search(String token) {
        return null;
    }

}