package com.docsearch.impl.regex;

import com.docsearch.impl.BaseDocumentSearchImpl;
import com.docsearch.model.FileNode;
import com.docsearch.model.ResultSetNode;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegExDocumentSearch extends BaseDocumentSearchImpl {

    public RegExDocumentSearch() {
        super();
    }

    public void setup(String[] files) throws IOException {
        super.setup(files);
    }

    public Queue<ResultSetNode> search(String token) {
        Pattern pattern = Pattern.compile(token);

        while (!fileNodes.isEmpty()) {
            FileNode fileNode = fileNodes.remove();

            int count = 0;

            List<String> lines = (List<String>) fileNode.getDetails();
            for (String line : lines) {
                count += searchToken(pattern, line);
            }

            ResultSetNode resultSetNode = new ResultSetNode(fileNode.getFileName(), count);
            queue.add(resultSetNode);
        }

        return queue;
    }


    @Override
    public void setAndValidateToken(String token) {
        super.setAndValidateToken(token);
        try {
            Pattern.compile(token);
        } catch (PatternSyntaxException e) {
            throw new RuntimeException("Invalid pattern entered! Exiting..");
        }
    }

    public int searchToken(Pattern pattern, String line) {
        int count = 0;
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            count++;
        }
        return count;
    }

}
