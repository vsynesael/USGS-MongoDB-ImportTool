README file for the Custom USGS DYFI open source data import tool to a MongoDB

Developed by V. Synesael, J. Godisart & D. Hill
Western University - London, ON - CS4411 : DB II

----------------------------------------------------------------------
Program Versions


----------------------------------------------------------------------
Program build instructions


----------------------------------------------------------------------
Database setup (prior to program execution)
1. A mongo database needs to be existing on a server, the program does not assume a username or password is needed to connect
2. The program also assumes that there is an existing database called "eartquake"
----------------------------------------------------------------------
Program running instructions
1. execute the jar with dependencies
		java -jar
2. program will ask for a folder path to the information to be imported
		the program expects this to eventually find contents.xml and event_data.xml files from the folder structure under dyfi
3. once the program has parsed all the files and created appropriate java objects of the data, it will ask for a mongo connection
		formatted mongodb:host_name:ip_address
		ie. mongodb://host:27017
			*NB this version of the program does not require user name or password for access our database, this can easily be added to the code base
4. from here the information should be automatically uploaded to your mongo dbms and inserted into a database called "earthquake"