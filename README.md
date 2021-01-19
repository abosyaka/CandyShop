# CandyShop

This is a candy store website. 

## Launch guide

* Create PostgreSQL database
* Provide database connection parameters in `db.properties` file in `resources` folder.
* Connect database with Inteliji Idea and run `CandyShop.sql` script. Then run `insertionScript.sql`
* In `web.xml` specify context param value of `upload.location` with your path, where images will be uploaded 

## Recomendations

* Tomcat ver 9 +
* To have an Internet connection
* Test on the Google Chrome web browser
* Images of goods will not be displayed until they are uploaded into folder specified in `web.xml`. 
  You can add them in admin panel.
  
