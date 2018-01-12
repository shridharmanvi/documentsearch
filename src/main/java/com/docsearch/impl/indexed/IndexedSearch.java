package com.docsearch.impl.indexed;

import com.docsearch.Search;
import com.docsearch.model.FileNode;
import com.docsearch.model.ResultSetNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class IndexedSearch implements Search {
    private LinkedList<FileNode> fileNodes;

    public IndexedSearch() {
        fileNodes = new LinkedList<>();
    }

    public void setup(String[] files) {

        for (String fileName : files) {

            FileReader fileReader = null;
            String line = null;
            try {
                fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                Map<String, Integer> counter = new HashMap<>();
                FileNode fileNode = new FileNode(fileName, counter);
                fileNodes.add(fileNode);

                while ((line = bufferedReader.readLine()) != null) {
                    String[] words = line.split(" ");
                    for (String word : words) {
                        int count = counter.getOrDefault(word, 0);
                        counter.put(word, count + 1);
                    }
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
            if (fileNode.getCounter().containsKey(token)) {
                count = fileNode.getCounter().get(token);
            }

            ResultSetNode finalCount = new ResultSetNode(fileNode.getFileName(), count);
            queue.add(finalCount);
        }

        return queue;
    }

}
