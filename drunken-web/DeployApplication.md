Instructions on how to run
==========================

1) install Java / Maven and make sure you have an internet connection
2) cd into root directory /drunken-robot
3) run: mvn package
4) copy drunken-robot/drunken-web/target/drunken.war to your web server.
5) the application should be deployed under the context localhost:8080/drunken


Using the application
====================

1) Adding cars:
http://localhost:8080/drunken/rest/car/add
set http content type: application/json
Sample Json POST:
{
"model":"Aston Martin",
"make":"One 77",
"manufactureDate":"12-09-2014"
}

2) Retrieving car:
Sample http GET containing {id} at the end of the URL
http://localhost:8080/drunken/rest/car/6

3) Removing car
Sample http POST containing {id} at the end of the URL
http://localhost:8080/drunken/rest/car/delete/1

4) Updating car:
You can only update a car if you pass in the ID of the car you want to change.
http://localhost:8080/drunken/rest/car/update
set http content type: application/json
Sample Json POST:
{
"id":"3",
"model":"Aston Martin",
"make":"One 77",
"manufactureDate":"12-09-2014"
}