# ToolXtractor
Extract tools from TEI-encoded abstracts against a matching list.

# Build with [Maven](https://maven.apache.org/)
```mvn clean package```

# Launch the tool
```java -jar target/ToolXtractor-full.jar -byTool -dir data/2015/ -inputTools src/main/resources/tools_teresah.txt```

## Options
Usage: ```java -jar ToolXtractor-full.jar```

 ```-byAbstract```
 Results will be a list of abstracts with each tool used
 
 ```-byTool```
 Results will be a list of tools with each abstract they are mentioned in
 
 ```-dir <arg>```
 Provide path to directory to parse
 
 ```-inputTools <arg>```
 A txt file with list of the tools (1 tool per line)
 
 ```-stopwords <arg>```
 A txt file with list of the stopwords (toolnames that you don't want to appear in the results) (1 tool per line)
 
 ```-reverse```
 Reverse the order of the result (by default ascending order)
 
 ```-verbose```
 Add extra verbose information

# Dataset
All under CC and retrieved from the ADHO github page: https://github.com/ADHO/

Only for the year [2017](https://github.com/ADHO/dh2017) we used the tool [Grobid](https://github.com/kermitt2/grobid/) to create XML-TEI files.

# Example of output
```
$ java -jar target/ToolXtractor-full.jar -byTool -dir data/2015/ -inputTools src/main/resources/tools_teresah.txt -stopwords src/main/resources/stopwords.txt -reverse
Start parsing the directories
Finished parsing the directories (in 1849ms)
Start searching in the files
Finish searching in the files (in 270ms)
There are 19 items listed below, and each have their own linked items
====================================================================================
Identifier: Gephi (11)
-- SIMPSON_John_Edward_Building_Better_Linked_Data___Ontol.xml
-- KITAMOTO_Asanobu_Senga__Participatory_Curation_and_the_.xml
-- HAMMOND_Jeremy_Social_Media_Data__Twitter_Scraping_on_N.xml
-- LIU_Jyi_Shane_A_Study_of_Symbolic_Element_Network_in_Na.xml
-- HEUSER_Ryan_James_Knowledge_Networks__Juxtaposed__Disci.xml
-- FALK_Michael_Gregory_Modelling_Genre_Using_Character_Ne.xml
-- SULA_Chris_Alen_Visual_First_Amendment__A_Case_Study_in.xml
-- BROWNE_Jeremy_LinkedIn_circa_2000_BCE__Towards_a_Networ.xml
-- SIMPSON_John_Edward_Exploring_Large_Datasets_with_Topic.xml
-- POIBEAU_Thierry_Generating_Navigable_Semantic_Maps_from_Social_Sciences_Corpora.xml
-- JAKACKI_Diane_Katherine_Pedagogical_Hermeneutics_and_Te.xml
====================================================================================
Identifier: MALLET (6)
-- LESTER_Connie_Lee_Using_Multiple_Strategies_To_Find_Con.xml
-- ORGANISCIAK_Peter_Remembering_books__A_within_book_topi.xml
-- SIMPSON_John_Edward_Exploring_Large_Datasets_with_Topic.xml
-- JOHNSON_ROBERSON_Chris_A_Susurrant__A_Tool_for_Algorith.xml
-- SCH_CH_Christof_Topic_Modeling_French_Crime_Fiction.xml
-- HOWE_Tonya_Novels_in_Context__A_TEI_Database_of_Primary.xml
====================================================================================
```
[...]
```
====================================================================================
Identifier: Versioning Machine (1)
-- J_NICKE_Stefan_A_Distant_Reading_Visualization_for_Vari.xml
====================================================================================
Identifier: KORA (1)
-- WATRALL_Ethan_Mbira__A_Platform_to_Build__Serve__and_Su.xml
Total time 2124ms
```
