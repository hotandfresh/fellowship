# Code Fellowship

## Overview
This is a Spring Boot application that connects coders with other coders.

The application was intialized on [the Spring website](https://start.spring.io/);

## How to Run the Application
Clone the repo [here](https://github.com/hotandfresh/codefellowship) and save it in a directory of your choice.  Open the application with an editor of your choice and hit the green arrow to run it.  Navigate to ```http://localhost:8080```

After cloning the repo, the application can be run from the root of the directory with the following terminal command: ```./gradlew run```

### Additional things needed to run the application

- Postgresql needs to be installed.  Login to psql Afterwards, create a db called ```fellowship```.  
 
 - Uncomment line 4 of application.properties. Replace ```spring.datasource.username``` and ```spring.datasource.password``` with your respective postgresql user name and password. 
 
 - Rebuild the application 

## End points
Add any of these endpoints to the end of ```http://localhost:8080```

```/``` : this is the home page. Those without an account can register by clicking on ```Sign Up```.  If they've already registered, they can click on ```login```.

```/signup``` : register for an account

```/login``` : login with proper credentials.

```/logout``` : logout of an account

```/myprofile``` : view a specific user account profile

```/users/{id}``` : view data about a single user

```/users```: displays all users

```/post```: add a post.  after adding, users are taken to their profile where their posts are displayed 
 
## Acknowledgements
Spring Authorization Steps were followed [here](https://github.com/codefellows/seattle-java-401d5/blob/master/SpringAuthCheatSheet.md).

## Resources
Code Reviews after each day!
