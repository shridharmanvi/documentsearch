package com.docsearch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentSearchMain {
    private static Logger LOG = LoggerFactory.getLogger(DocumentSearchMain.class);

    public static void main(String[] args) {

        LOG.info("Starting document search...");
        DocumentSearchDriver driver = new DocumentSearchDriver();

        driver.getUserInputAndSetUp();

        driver.performSearch();
        driver.printRelevancy();

        LOG.info("Document search complete!");
    }

}
