package com.docsearch.impl;

import com.docsearch.DocumentSearch;
import com.docsearch.impl.indexed.IndexedDocumentSearch;
import com.docsearch.impl.regex.RegExDocumentSearch;
import com.docsearch.impl.simple.SimpleDocumentSearch;

public class SearchFactory {

    public static DocumentSearch getSearchObj(int method) {
        DocumentSearch searchMethod = null;

        switch (method) {
            case 1:
                searchMethod = new SimpleDocumentSearch();
                break;
            case 2:
                searchMethod = new RegExDocumentSearch();
                break;
            case 3:
                searchMethod = new IndexedDocumentSearch();
                break;
            default:
                throw new RuntimeException("Invalid search method!");
        }
        return searchMethod;
    }

}
