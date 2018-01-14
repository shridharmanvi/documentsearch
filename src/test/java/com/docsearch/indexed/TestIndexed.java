package com.docsearch.indexed;

import com.docsearch.TestSearch;
import com.docsearch.impl.indexed.IndexedDocumentSearch;
import com.docsearch.model.ResultSetNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class TestIndexed implements TestSearch {

    private IndexedDocumentSearch indexedDocumentSearch;

    @Before
    public void setUp() {
        indexedDocumentSearch = new IndexedDocumentSearch();
        String testDir = "/Users/shridhar.manvi/Downloads/sample_text/";
        String[] files = {testDir + "french_armed_forces.txt", testDir + "hitchhikers.txt", testDir + "warp_drive.txt"};
        try {
            indexedDocumentSearch.setup(files);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read files! Aborting..");
        }
    }

    @Override
    @Test
    public void searchToken() {
//        String line = "This is a sample sample test line test test Test!";
//
//        String token1 = "is";
//        assertEquals(1, indexedDocumentSearch.searchToken(token1, line));
//
//        String token2 = "sample";
//        assertEquals(2, indexedDocumentSearch.searchToken(token2, line));
//
//        String token3 = "test";
//        assertEquals(3, indexedDocumentSearch.searchToken(token3, line));
//
//        String token4 = "Test";
//        assertEquals(1, indexedDocumentSearch.searchToken(token4, line));
//
//        String token5 = "absent";
//        assertEquals(0, indexedDocumentSearch.searchToken(token5, line));
    }

    @Override
    @Test
    public void testSearch() {
        Queue<ResultSetNode> expectedQueue = new PriorityQueue<>(
                (ResultSetNode a, ResultSetNode b)
                        -> b.getCount() - a.getCount());

        ResultSetNode resultSetNode1 = new ResultSetNode("/file1", 21);
        ResultSetNode resultSetNode2 = new ResultSetNode("file2", 57);
        ResultSetNode resultSetNode3 = new ResultSetNode("file3", 6);

        expectedQueue.add(resultSetNode1);
        expectedQueue.add(resultSetNode2);
        expectedQueue.add(resultSetNode3);

        Queue<ResultSetNode> actualQueue = indexedDocumentSearch.search("the");

        while (!expectedQueue.isEmpty()) {
            ResultSetNode actualNode = actualQueue.poll();
            ResultSetNode expectedNode = expectedQueue.poll();

            assertEquals(expectedNode.getCount(), actualNode.getCount());
        }
    }
}
