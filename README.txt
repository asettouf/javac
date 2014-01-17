**********************README******************************************************************
**********************PLEASE!*****************************************************************
**********************************************************************************************
**********************AUTHORS*****************************************************************
**********ADNANE*********************ADONIS***************************************************
**********LOUARTI********************SETTOUF**************************************************
**********************************************************************************************
**********************************************************************************************


-GOAL :

The purpose of this application is to read inputs from text files, and transfer these datas to a database.

-GENERAL IDEA :

The principle on which is based this application is simple : 

*Check if the default directory or the specified directory exists (check the default settings section for more details)

*List all files in the directory

*Check each line of each file

*WARNING : IF A SINGLE LINE IS INVALID (not respecting the required format) THE FILE WILL BE ARCHIVED IN THE WRONG DIRECTORY (see below)

*After checking a file, the file is archived either in the right directory (if every check were positive) or the wrong directory
*Theses directories are located below the main directory used (i.e. assuming the path is : C:\Users\MyUserName\toto, the right directory will be located at
*C:\User\MyUserName\toto\right.date, the date will be : day-month-year-hour-minute-second, thus ensuring a unique timestamp for each run )

*During the checking, each valid data is collected 

*Then every data is transfered to the default database (see default section), thus BE SURE to have launched your database before using the programm

-DEFAULT SETTINGS:

*Database is "db" whatsoever (for the moment)

*Default directory : C:\Users\MyUserName\MusicToRead



-REQUIREMENTS :

*The application (of course!)

*A database initialised, USING HSQLDB, locally with the name "db", thus your connection url should be : jdbc:hsqldb:hsql://localhost/db
*(we tried to allow you to be able to use any name, after many errors we droped the idea for now)

*In your directory, you should have files with the extent : .music (i.e., "file1.music")

*Each line must look like this : CodeArtiste (integer!), NomArtiste (string), CodeAlbum (integer), NomAlbum (string), CodeChanson(integer),NomChanson(string), DureeChanson(integer)
*Attention : no check is made at a logical level thus you may have for instance : Artiste1 with Album1 and Artiste2 with Album1 (be sure to check your configuration files)

-RUN :

* For any first run (meaning your database has no tables), you must enter the argument "--initdb" to launch the script that creates the table

*If you want to use a custom directory, it MUST be located under MyUserName folder, which means that if you want the app to read from the directory "toto"
*it must be like this in the directory tree (it should work under Linux with its equivalent) : "C:\Users\MyUserName\toto" is correct, however "C:\toto" is wrong

*To use a custom directory you must enter as argument "--directory dirname"

-OTHERS :

*The logs are located at the root of the projects with the name "appli.log"

*A test file is given with the project with the name of "test"

Please if you were to check any bugs, please send us a mail, we will respond as quickly as possible. And if you have any question don't hesitate to contact us.

Cheers.






