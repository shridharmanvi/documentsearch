package com.docsearch.impl.simple;

import com.docsearch.impl.BaseDocumentSearchImpl;
import com.docsearch.model.FileNode;
import com.docsearch.model.ResultSetNode;

import java.io.IOException;
import java.util.*;

public class SimpleDocumentSearch extends BaseDocumentSearchImpl {

    public SimpleDocumentSearch() {
        super();
    }

    public void setup(String[] files) throws IOException {
        super.setup(files);
    }

    public Queue<ResultSetNode> search(String token) {
        while (!fileNodes.isEmpty()) {
            FileNode fileNode = fileNodes.remove();

            int count = 0;

            List<String> lines = (List<String>) fileNode.getDetails();
            for (String line : lines) {
                count += searchToken(token, line);
            }

            ResultSetNode resultSetNode = new ResultSetNode(fileNode.getFileName(), count);
            queue.add(resultSetNode);
        }

        return queue;
    }

    public int searchToken(String token, String line) {
        int count = 0;
        for (String word : line.split(" ")) {
            if (word.equals(token)) count++;
        }
        return count;
    }
}
