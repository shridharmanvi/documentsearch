package com.docsearch;

import com.docsearch.impl.SearchFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Running performance tests in this class
 */
public class PerformanceTest {

    public static void main(String[] args) throws IOException {
        PerformanceTest performanceTest = new PerformanceTest();

        performanceTest.buildTokens();
        performanceTest.runTest();
    }

    /**
     * This could also be made to read files and pick random words from it
     *
     * @return
     */
    private List<String> buildTokens() {
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

    private String getRandomWord(List<String> tokens) {
        return tokens.get((int) (Math.random() * tokens.size()));
    }

    @Test
    public void runTest() throws IOException {
        System.out.println("PERFORMANCE TEST: ");
        int[] documentSearchTypes = {1, 2, 3};
        List<String> allTokens = buildTokens();

        // Perform test for each search method
        for (int documentSearchType : documentSearchTypes) {
            int iterations = 2000000;
            int stepSize = 20000;

            // Initial set up for each search implementation
            DocumentSearch search = SearchFactory.getSearchObj(documentSearchType);


            long start;
            long end;
            long total = 0;

            while (iterations > 0) {
                String token = getRandomWord(allTokens);
                search.setAndValidateToken(token);
                search.setup(getInputFileNames());

                start = System.currentTimeMillis();

                for (int i = 0; i < stepSize; i++) {
                    search.search(token);
                }
                end = System.currentTimeMillis();

                iterations -= stepSize;
                total += end - start;
            }

            System.out.println("Method " + documentSearchType + " took " + total + "ms for 2000000 iterartions.");

        }
    }

    private String[] getInputFileNames() {
        String[] files = {"french_armed_forces.txt", "hitchhikers.txt", "warp_drive.txt"};
        String inputDir = "src/test/resources/test_files/";
        String[] fullNames = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            fullNames[i] = inputDir + files[i];
        }
        return fullNames;
    }
}
