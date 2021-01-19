# CandyShop

This is a candy store website. New user can sign up and log in. There are two roles: admin and user.
Admin has an access to the admin panel. Admin can add new good, user, category; edit good, user, category and order status; delete good, user and category.
User is able to search goods by category and name; add good to the cart; make order; browse his orders;

## Launch guide

* Create PostgreSQL database
* Provide database connection parameters in `db.properties` file in `resources` folder.
* Connect database with Inteliji Idea and run `CandyShop.sql` script. Then run `insertionScript.sql`
* In `web.xml` specify context param value of `upload.location` with your path, where images will be uploaded 

## Recomendations

* Tomcat ver 9 +
* Internet connection
* Test on the Google Chrome web browser
* Images of goods will not be displayed until they are uploaded into folder specified in `web.xml`. 
  You can add them in admin panel.
  
