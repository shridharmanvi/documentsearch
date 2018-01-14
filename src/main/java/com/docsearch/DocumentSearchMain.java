package com.docsearch;


public class DocumentSearchMain {

    public static void main(String[] args) {

        DocumentSearchDriver driver = new DocumentSearchDriver();

        driver.getUserInputAndSetUp();
        driver.performSearch();
        driver.printRelevancy();
    }

}
