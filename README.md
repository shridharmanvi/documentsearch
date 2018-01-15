## Document search 

This program searches a string through documents and ranks the documents in terms of the number of occurances of the term

Search can be performed using 3 different approaches:
1. Simple search (String matching)
2. Regex Search (Using pattern matching)
3. Indexed Search (The content is preprocessed and indexed)

##### How to build and execute?

```bash
# Step1: Clone the repository
git clone https://github.com/shridharmanvi/documentsearch.git
 
# Step2: Navigate to project source directory
cd documentsearch
 
# Step3: build the project
mvn clean package
 
# Step4: Make a new config file and define paths to input files
vim config.properties
 
inputfiles.dir=/path/to/inputfiles/dir
inputfiles.files=file1.txt,file2.txt,file3.txt

# Step 5: Execute the program
java -Dproperties=config.properties -jar DocumentSearchMain.jar

```


##### How to test?
The below command will run all unit tests. 
``
mvn clean test
``

##### Performance test

Performance testing is done by calling the search method of each of the implementations 2000000 times and comparing the 
run times. At a step size of 20,000 a new random token is chosen. The total is recorded over all of the iterations.

To run the performance test simply execute the PerformanceTest main jar
`java -jar PerformanceTest.jar`


##### Stats/Results

Upon running performance test for each of the implementations, it can be seen that indexed implementation is the fastest.
The reason being, the documents are pre-indexed before calling the search method and words are indexed using a hashmap. 
The lookup time for a hash map is an O(1) operation. Whereas the lookup time for both simple string matching and 
pattern matching are O(n) since they have to iterate over the file content on every search request.
 

```bash
PERFORMANCE TEST: 
Method 1 took 249ms for 2000000 iterartions.
Method 2 took 1281ms for 2000000 iterartions.
Method 3 took 22ms for 2000000 iterartions.
```

##### Further enhancements:

* Indexed approach is the best for document search. However the solution implemented for this 
exercise is primitive as it cannot be easily scaled
* If the volume of test files increase there are chances of running into memory issues on a single node
* We could make the app distributed by making it a RESTful service and using a load balancer to distribute the 
load and store the counters in an in-memory key-value database like redis
* An alternative could be to use a distributed indexing system like Solr/lucene/elastic search
* These systems are distributed over multiple nodes (cluster) and can be scaled horizontally in case the data 
volume increases and also provide built in fault tolerance capabilities





