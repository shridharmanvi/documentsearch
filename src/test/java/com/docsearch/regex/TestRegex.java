package com.docsearch.regex;

import com.docsearch.TestSearch;
import com.docsearch.impl.regex.RegExDocumentSearch;
import com.docsearch.model.ResultSetNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TestRegex implements TestSearch {

    private RegExDocumentSearch regExSearch;

    @Before
    public void setUp() {
        regExSearch = new RegExDocumentSearch();
        String testDir = "/Users/shridhar.manvi/Downloads/sample_text/";
        String[] files = {testDir + "french_armed_forces.txt", testDir + "hitchhikers.txt", testDir + "warp_drive.txt"};
        try {
            regExSearch.setup(files);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read files! Aborting..");
        }
    }


    @Override
    @Test
    public void searchToken() {
        String line = "This is a sample sample test line test test Test!";

        String token1 = "is";
        assertEquals(2, regExSearch.searchToken(Pattern.compile(token1), line));

        String token2 = "sample";
        assertEquals(2, regExSearch.searchToken(Pattern.compile(token2), line));

        // Case sensitivity regex check
        String token3 = "(?i)test";
        assertEquals(4, regExSearch.searchToken(Pattern.compile(token3), line));

        String token4 = "Test";
        assertEquals(1, regExSearch.searchToken(Pattern.compile(token4), line));

        String token5 = "absent";
        assertEquals(0, regExSearch.searchToken(Pattern.compile(token5), line));
    }

    @Override
    @Test
    public void testSearch() {
        Queue<ResultSetNode> expectedQueue = new PriorityQueue<>(
                (ResultSetNode a, ResultSetNode b)
                        -> b.getCount() - a.getCount());

        ResultSetNode resultSetNode1 = new ResultSetNode("/file1", 59);
        ResultSetNode resultSetNode2 = new ResultSetNode("file2", 24);
        ResultSetNode resultSetNode3 = new ResultSetNode("file3", 9);

        expectedQueue.add(resultSetNode1);
        expectedQueue.add(resultSetNode2);
        expectedQueue.add(resultSetNode3);

        Queue<ResultSetNode> actualQueue = regExSearch.search("the");

        while (!expectedQueue.isEmpty()) {
            ResultSetNode actualNode = actualQueue.poll();
            ResultSetNode expectedNode = expectedQueue.poll();

            assertEquals(expectedNode.getCount(), actualNode.getCount());
        }
    }
}
