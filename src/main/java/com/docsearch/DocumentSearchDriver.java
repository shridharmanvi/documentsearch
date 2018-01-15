package com.docsearch;

import com.docsearch.impl.SearchFactory;
import com.docsearch.model.ResultSetNode;
import com.docsearch.utils.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Queue;
import java.util.Scanner;

/**
 * Class responsible for driving the document search program
 */
class DocumentSearchDriver {

    private int method;
    private String token;
    private DocumentSearch documentSearch;
    private Queue<ResultSetNode> relevancy;
    private static Properties config;
    private static Logger LOG = LoggerFactory.getLogger(DocumentSearchMain.class);


    DocumentSearchDriver() {
        config = new Properties();
        relevancy = null;
        documentSearch = null;
        method = 0;
        token = null;
    }

    void getUserInputAndSetUp() {
        try (Scanner scan = new Scanner(System.in)) {

            System.out.println("Enter the search token:");
            token = scan.nextLine();

            LOG.debug("User entered token: {}", token);

            System.out.println("Enter Search Method:\n"
                    + "\t1) Simple search (String matching)\n"
                    + "\t2) Regular Expression matching\n"
                    + "\t3) Indexed search\n");
            method = scan.nextInt();

            LOG.debug("User entered search method: {}", method);

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
        LOG.debug("Relevancy:");
        while (!relevancy.isEmpty()) {
            ResultSetNode resultSetNode = relevancy.remove();
            LOG.debug("File: {}, count: {} ", resultSetNode.getFilename(), resultSetNode.getCount());
            System.out.println("File: " + resultSetNode.getFilename() + "Count: " + resultSetNode.getCount());
        }
    }

    private String[] getInputFileNames() {
        config = ConfigLoader.getConfig();
        String[] files = config.getProperty("inputfiles.files").split(",");
        String inputDir = config.getProperty("inputfiles.dir");
        String[] fullNames = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            fullNames[i] = inputDir + files[i];
        }
        return fullNames;
    }
}
