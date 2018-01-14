package com.docsearch;

import com.docsearch.impl.SearchFactory;
import com.docsearch.model.ResultSetNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Queue;
import java.util.Scanner;

/**
 * Class responsible for driving the document search program
 */
public class DocumentSearchDriver {

    private int method;
    private String token;
    private DocumentSearch documentSearch;
    private Queue<ResultSetNode> relevancy;
    private static Properties config;

    public DocumentSearchDriver() {
        relevancy = null;
        documentSearch = null;
        method = 0;
        token = null;
        readConfig();
    }

    public void getUserInputAndSetUp() {
        try (Scanner scan = new Scanner(System.in)) {

            System.out.println("Enter the search token:");
            token = scan.nextLine();

            System.out.println("Enter Search Method:\n"
                    + "\t1) Simple search (String matching)\n"
                    + "\t2) Regular Expression matching\n"
                    + "\t3) Indexed search\n");
            method = scan.nextInt();


            documentSearch = SearchFactory.getSearchObj(method);
            documentSearch.setAndValidateToken(token);

            try {
                documentSearch.setup(getInputFileNames());
            } catch (Exception e) {
                throw new RuntimeException("Cannot read files! Exiting..");
            }
        }
    }

    void performSearch() {
        relevancy = documentSearch.search(token);
    }

    void printRelevancy() {
        while (!relevancy.isEmpty()) {
            ResultSetNode resultSetNode = relevancy.remove();

            System.out.println("File: " + resultSetNode.getFilename() + "Count: " + resultSetNode.getCount());
        }
    }

    private String[] getInputFileNames() {
        String[] files = config.getProperty("inputfiles.files").split(",");
        String inputDir = config.getProperty("inputfiles.dir");
        String[] fullNames = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            fullNames[i] = inputDir + files[i];
        }
        return fullNames;
    }

    private Properties readConfig() {
        if (config == null) {
            try {
                InputStream inputStream = DocumentSearchDriver.class
                        .getClassLoader()
                        .getResourceAsStream("config.properties");

                config.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Cannot read properties file!!");
            }
        }
        return config;
    }
}
