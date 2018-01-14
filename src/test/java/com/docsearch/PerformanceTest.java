package com.docsearch;

import com.docsearch.impl.SearchFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.docsearch.DocumentSearchDriver.getInputFileNames;

/**
 * Running performance tests in this class
 */
public class PerformanceTest {

    public static void main(String[] args) {

    }

    public List<String> generateRandomWords() {
        List<String> tokens = new ArrayList<>();

        tokens.add("the");
        tokens.add("and");
        tokens.add("Babylon");
        tokens.add("fire");
        tokens.add("conflict");
        tokens.add("use");
        tokens.add("victory");
        tokens.add("these");
        tokens.add("title");

        return tokens;
    }

    public String getRandomWord(List<String> tokens) {
        return tokens.get((int) (Math.random() * tokens.size()));
    }

    @Test
    public void runTest() throws IOException {
        int[] documentSearchTypes = {1, 2, 3};
        List<String> allTokens = generateRandomWords();
        String token = "the";

        // Perform test for each search method
        for (int documentSearchType : documentSearchTypes) {
            int iterations = 200000;

            // Initial set up for each search implementation
            DocumentSearch search = SearchFactory.getSearchObj(documentSearchType);
            search.setup(getInputFileNames());
            search.setAndValidateToken(token);

            long start = System.currentTimeMillis();
            long end = 0;
            long avg;

            for (int j = 0; j < 10; j++) {
                for (int i = 0; i < iterations; i++) {
                    search.search(token);
                }
                end = System.currentTimeMillis();
            }

            avg = end - start;

            System.out.println("Method " + documentSearchType + " took " + avg + "ms for 2000000 iterartions.");

        }
    }
}
