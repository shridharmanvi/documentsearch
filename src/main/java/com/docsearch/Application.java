package com.docsearch;

import com.docsearch.impl.indexed.IndexedSearch;
import com.docsearch.impl.regex.RegExSearch;
import com.docsearch.impl.simple.SimpleSearch;
import com.docsearch.model.ResultSetNode;

import java.util.Queue;


public class Application {

    /**
     * Read properties file here
     */
    static {

    }

    public static Search getSearchObj(int method) {
        Search searchMethod = null;

        switch (method) {
            case 1:
                searchMethod = new SimpleSearch();
                break;
            case 2:
                searchMethod = new RegExSearch();
                break;
            case 3:
                searchMethod = new IndexedSearch();
                break;
            default:
                break;
        }

        return searchMethod;
    }

    public static void main(String[] args) {
        Search search = Application.getSearchObj(3);

        // Start app by passing file names as args
        // Prompt for method of search
        // Load files in appropriate class
        // Ask for search token
        // Return output

        String dirName = "/Users/shridhar.manvi/Downloads/sample_text/";

        String[] files = {dirName + "french_armed_forces.txt",
                dirName + "hitchhikers.txt",
                dirName + "warp_drive.txt"
        };

        search.setup(files);
        Queue<ResultSetNode> results = search.search("Babylon");

        while (!results.isEmpty()) {
            ResultSetNode result = results.poll();
            System.out.println("Filename: " + result.getFilename() + " " + result.getCount());
        }
    }
}
