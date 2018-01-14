package com.docsearch.impl;

import com.docsearch.DocumentSearch;
import com.docsearch.impl.indexed.IndexedDocumentSearch;
import com.docsearch.impl.regex.RegExDocumentSearch;
import com.docsearch.impl.simple.SimpleDocumentSearch;

import java.util.HashMap;
import java.util.Map;

public class SearchFactory {
    private static Map<Integer, DocumentSearch> factory;

    static {
        factory = new HashMap<>();
        populateFactory();
    }

    private static void populateFactory() {
        factory.put(1, new SimpleDocumentSearch());
        factory.put(2, new RegExDocumentSearch());
        factory.put(3, new IndexedDocumentSearch());
    }

    public static DocumentSearch getSearchObj(int method) {
        if (factory.containsKey(method)){
            return factory.get(method);
        } else {
            throw new RuntimeException("Cannot find implementation!");
        }
    }

}
