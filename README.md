### Custom USGS DYFI open source data import tool to a MongoDB

#### Developed by J. Godisart, D. Hill & V. Synesael
#### Western University - London, ON - CS4411 : DB II - Winter 2016

----------------------------------------------------------------------
##### Program Versions
*ImportTool-1.0-SNAPSHOT* : initial release of the import tool which imports the dyfi folder of data to MongoDB.<br>
	- Data not imported: Blobs of file content data

----------------------------------------------------------------------
*Program build instructions*


----------------------------------------------------------------------
*Database setup (prior to program execution)*<br>
1. A mongo database needs to be existing on a server, the program does not assume a username or password is needed to connect<br>
2. Connect to the mongo database server<br>
3. The program also assumes that there is an existing database called "eartquake"<br>

----------------------------------------------------------------------
*Program running instructions*<br>
1. execute the jar with dependencies<br>
... _java -jar ImportTool-1.0-SNAPSHOT-jar-with-dependencies.jar_
2. program will ask for a folder path to the information to be imported<br>
... the program expects this to eventually find contents.xml and event_data.xml files from the folder structure under dyfi<br>
3. once the program has parsed all the files and created appropriate java objects of the data, it will ask for a mongo connection<br>
... formatted _mongodb:host_name:ip_address_<br>
... ie. mongodb://host:27017<br>
... NB this version of the program does not require user name or password for access to our database<br>
4. from here the information should be automatically uploaded to your mongo dbms and inserted into a database called "earthquake" under the collection "dyfi"<br>