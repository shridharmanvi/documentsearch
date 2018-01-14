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

        // Perform test for each search method
        for (int documentSearchType : documentSearchTypes) {
            int iterations = 2000000;

            // Initial set up for each search implementation
            DocumentSearch search = SearchFactory.getSearchObj(documentSearchType);
            search.setup(getInputFileNames());


            long start;
            long end;
            long total = 0;

            for (int j = 0; j < 10; j++) {
                // Get random search token
                String token = getRandomWord(allTokens);
                search.setAndValidateToken(token);


                start = System.currentTimeMillis();
                for (int i = 0; i < iterations / 10; i++) {
                    search.search(token);
                }
                end = System.currentTimeMillis();
                total += end - start;
            }

            System.out.println("Method " + documentSearchType + " took " + total + "ms for 2000000 iterartions.");

        }
    }
}
