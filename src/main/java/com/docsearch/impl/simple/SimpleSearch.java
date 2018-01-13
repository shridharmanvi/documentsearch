package com.docsearch.impl.simple;

import com.docsearch.Search;
import com.docsearch.impl.FileUtils;
import com.docsearch.model.FileNode;
import com.docsearch.model.ResultSetNode;

import java.io.BufferedReader;
import java.util.*;

public class SimpleSearch implements Search {
    private List<String> lines;
    private FileUtils fileUtils;
    private LinkedList<FileNode> fileNodes;

    public SimpleSearch() {
        fileUtils = new FileUtils();
        lines = new LinkedList<>();
    }


    public void setup(String[] files) {
        for (String fileName : files) {
            String line;
            try {
                BufferedReader bufferedReader = fileUtils.readFile(fileName);
                while ((line = bufferedReader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Queue<ResultSetNode> search(String token) {
        Queue<ResultSetNode> queue = new PriorityQueue<>((ResultSetNode a, ResultSetNode b) -> b.getCount() - a.getCount());

        while (!fileNodes.isEmpty()) {
            FileNode fileNode = fileNodes.remove();
            int count = 0;

            List<String> lines = (List<String>) fileNode.getDetails();
            for (String line: lines) {
                count += searchToken(token, line);
            }



        }

        return null;
    }

    private int searchToken(String token, String line) {
        int count = 0;
        for (String word : line.split(" ")) {
            if (word.equals(token)) count++;
        }
        return count;
    }

}
