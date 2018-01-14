package com.docsearch.simple;

import com.docsearch.TestSearch;
import com.docsearch.impl.simple.SimpleDocumentSearch;
import com.docsearch.model.ResultSetNode;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class TestSimple implements TestSearch {

    private SimpleDocumentSearch simpleSearch;

    private static String TOKEN = "Test";

    @Before
    public void setUp() {
        simpleSearch = new SimpleDocumentSearch();
    }


    @Override
    @Test
    public void searchToken() {
        String line = "This is a sample sample test line test test Test!";

        String token1 = "is";
        assertEquals(1, simpleSearch.searchToken(token1, line));

        String token2 = "sample";
        assertEquals(2, simpleSearch.searchToken(token2, line));

        String token3 = "test";
        assertEquals(3, simpleSearch.searchToken(token3, line));

        String token4 = "Test";
        assertEquals(1, simpleSearch.searchToken(token4, line));

        String token5 = "absent";
        assertEquals(0, simpleSearch.searchToken(token5, line));
    }

    @Override
    @Test
    public void testSearch() {
        Queue<ResultSetNode> results = simpleSearch.search(TOKEN);

        while (!results.isEmpty()) {
            ResultSetNode result = results.remove();
            System.out.println(result.getFilename() + " " + result.getCount());
        }
    }
}
