package com.docsearch.impl.indexed;

import com.docsearch.DocumentSearch;
import com.docsearch.impl.FileUtils;
import com.docsearch.model.FileNode;
import com.docsearch.model.ResultSetNode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class IndexedDocumentSearch implements DocumentSearch {
    private LinkedList<FileNode> fileNodes;
    private FileUtils fileUtils;
    private static String SPECIALCHARS = "[-+.^:,]";

    public IndexedDocumentSearch() {
        fileUtils = new FileUtils();
        fileNodes = new LinkedList<>();
    }

    public void setup(String[] files) throws IOException {

        for (String fileName : files) {
            String line = null;

            BufferedReader bufferedReader = fileUtils.readFile(fileName);

            Map<String, Integer> counter = new HashMap<>();
            FileNode fileNode = new FileNode(fileName, counter);
            fileNodes.add(fileNode);

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    int count = counter.getOrDefault(word.replaceAll(SPECIALCHARS, ""), 0);
                    counter.put(word, count + 1);
                }
            }
        }
    }

    @Override
    public void setAndValidateToken(String token) {
        if (token.isEmpty()) {
            throw new RuntimeException("");
        }
    }

    public Queue<ResultSetNode> search(String token) {
        Queue<ResultSetNode> queue = new PriorityQueue<>((ResultSetNode a, ResultSetNode b) -> b.getCount() - a.getCount());

        while (!fileNodes.isEmpty()) {
            FileNode fileNode = fileNodes.remove();
            Map<String, Integer> counter = (Map<String, Integer>) fileNode.getDetails();
            int count = 0;

            if (counter.containsKey(token)) count = counter.get(token);

            ResultSetNode finalCount = new ResultSetNode(fileNode.getFileName(), count);
            queue.add(finalCount);
        }

        return queue;
    }

}
