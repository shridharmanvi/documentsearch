package com.docsearch.simple;

import com.docsearch.TestSearch;
import com.docsearch.impl.simple.SimpleDocumentSearch;
import com.docsearch.model.ResultSetNode;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

public class TestSimple implements TestSearch {

    private SimpleDocumentSearch simpleSearch;
    private String[] files;
    private String line = "This is a sample sample test line test test Test!";
    private static String TOKEN = "Test";

    @Before
    public void setUp() {
        simpleSearch = new SimpleDocumentSearch();
        // simpleSearch.setup();
    }


    @Override
    @Test
    public void searchToken() {
        int count = simpleSearch.searchToken(TOKEN, line);
        System.out.println(count);
    }

    @Override
    @Test
    public void testDataLoad() {
        // Test data load from files into container here

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
