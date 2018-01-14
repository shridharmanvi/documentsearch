package com.docsearch.regex;

import com.docsearch.TestSearch;
import com.docsearch.impl.regex.RegExDocumentSearch;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

public class TestRegex implements TestSearch {

    private RegExDocumentSearch regExSearch;
    private String line = "This is a sample sample test line test test Test!";
    private static String TOKEN = "Test!";

    @Before
    public void setUp() {
        regExSearch = new RegExDocumentSearch();
    }


    @Override
    @Test
    public void searchToken() {

    }

    @Override
    @Test
    public void testSearch() {
        Pattern pattern = Pattern.compile(TOKEN);

        int count = regExSearch.searchToken(pattern, line);
        System.out.println(count);

    }
}
