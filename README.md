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