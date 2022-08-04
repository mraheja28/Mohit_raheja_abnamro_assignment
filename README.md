# ABN-Amro-Assessment: Recipe Web Service
Recipe Web Service is specifically designed and implemented as part of ABN Amro Technical Interview. Recipe Web Service contains ReST APIs in order to Create, Get, Update and Delete recipe from the database and render the requested details as JSON response to end user. The response from ReST APIs can be further integrated with front end view for better presentation.

###Objective
Create a standalone java application which allows users to manage their favourite recipes. It should allow adding, updating, removing and fetching recipes. Additionally users should be able to filter available recipes based on one or more of the following criteria:
1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude)
4. Text search within the instructions.
For example, the API should be able to handle the following search requests:
• All vegetarian recipes
• Recipes that can serve 4 persons and have “potatoes” as an ingredient
• Recipes without “salmon” as an ingredient that has “oven” in the instructions.

### System Design
Recipe Web Service is microservice based layered architectured RESTful Web Service. This service can be deployed independently on premise / cloud and can also be containerized to execute as docker containers. There are 4 layers from top to bottom:
- API Layer
  - Top layer, which is main interface available for intgeration and interaction with front-end or end user to consume APIs
  - Contains secured API end points implementation
  - [Springboot-starter-security](https://spring.io/guides/gs/securing-web/) Module along with JWT is used to implement authentication for APIs 
  - [Springboot-starter-web](https://spring.io/guides/gs/rest-service/) module used as a framework to implement ReSTful api end points  
- Service Layer
  - This layer sits in between API layer and Data access layer with some utility functionality
  - Mainly responsible for interacting with Data Access Layer and transferring the recipes data as required by top and below layers
  - It's just another module added to decouple business logic of recipes data transfer and mapping from/to API layer
  - Further, service layer can be enhanced to support advanved features like Caching, Interacting with external Authorization Service etc
- Data Access Layer
  - Responsible to provide Object Relationship Mapping (ORM) between higher level recipe Java objects and persistence layer tables
  - [Springboot-starter-data-JPA](https://spring.io/guides/gs/accessing-data-jpa/) module is used to implement mappings between objects and tables
  - This layer contains recipe entity classes and JPA repositories which implement lower level functionality of storing/retrieving recipes data  
- Persistence Layer
  - Bottom most layer, responsible for physically storing the recipes data onto database table
  - Just one physical table - `recipes` is used to store the recipes data for the service
  - [MySQL]((https://www.mysql.com/) is configured to be used as database service
  - For development and testing purposes, the Embedded H2 Database provided by Spring Boot framework is also utilized 

### Webservice API Flow
![Recipe Webservice API Flow](https://github.com/gautammroads/ABN-amro-demo/blob/develop/recipes-service-flow.pdf)

### Supported Features
Feature | Software Module Used
------------ | -------------
ReSTful API | [Springboot](https://spring.io/projects/spring-boot)
API Authentication | [Spring Security](https://spring.io/projects/spring-security) with JWT Token
Object Relationship Mapping | [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
Exception Handling | [Controller Advice and ExceptionHandler](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)
Logging | [SLF4J](http://www.slf4j.org/manual.html) Logger
Unit Tests | Junit 5 with [AssertJ](https://assertj.github.io/doc/)

### Prerequisites
* [JDK 1.11](https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html)
* [Apache Maven](https://maven.apache.org/)
* [MySQL](https://www.mysql.com/)
* [Git](https://git-scm.com/)

### Steps to build Web Service
* Download code zip / `git clone https://github.com/mraheja28/Mohit_raheja_abnamro-assignment.git`
* Move to `ABN-Amro-Assessment` and run maven build command `mvn clean package`
* To build by skipping unit tests run maven command `mvn clean package -DskipTests`
* On successfull build completion, one should have web service jar in `target` directory named as `Recipes-Service-1.0.jar`

### Steps to execute Web Service
* **Execution on Development profile with Embedded H2 Database**
  - In Development Mode, by default web service uses [Embedded H2 database](https://spring.io/guides/gs/accessing-data-jpa/) for persisting and retrieving recipes details.
  - Command to execute: 
   ```
        java -jar target/Recipes-Service-1.0.jar --spring.profiles.active=dev --logging.level.root=INFO
   ```
  - On successfull start, one should notice log message on console `Tomcat started on port(s): 8080 (http)` and have web service listening for web requests at port 8080
* **Execution on Development profile with MySQL Database**
  - In Development mode, one can also execute web service against local [MySQL Service](https://www.mysql.com/) for persisting and retrieving recipes details.     

### Web Service ReST API End Points
Recipe Webservice comes with ReST API Ends points for authentication, creating a new recipe, retrieveing an existing recipe, retrieving all existing recieps as list, updating an an existing recipe and deleting an existing recipe. Below table lists and describes on the implemented ReST APIs:
**Note: With all given below api end points request, make sure to include header `Content-Type as application/json`**
API End Point | Method | Purpose | Request | Response
------------ | ------------- | ------------- | ------------ | ------------- 
/api/v1/add-recipe | POST | Create a new recipe | Recipe Model DTO| Recipe Model with 201 Created on Success, 400 Bad request on failure
/api/recipe/{id} | GET | Get an existing recipe | Recipe id as path parameter and valid JWT Token as bearer token as auth header | Recipe Model with 200 OK on Success, 401 Not Found on failure
/api/v1/update-recipe | PUT | Update an existing recipe | Updated Recipe Model | Recipe Model with 200 OK on Success, 401 Not Found on failure
/api/v1/remove-recipe | DELETE | Delete an existing recipe | Recipe id as request parameter  | Deletion message with 200 OK on success, 401 Not Found on failure
/api/v1/list-all-recipe | GET | Get all Recipes |  | Recipe Model with 200 OK on Success, 401 Not Found on failure
/api/v1/find-recipe-by-vegetarian | GET | Get all Recipes that are vegetarian/non-vegetarian | Request parameter boolean vegetarian=true/false | Recipe Model with 200 OK on Success, 401 Not Found on failure
/api/v1/find-recipe-by-instruction | GET | Get all Recipes based on a specific instructions | Request parameter String search-text=<search-text> | Recipe Model with 200 OK on Success, 401 Not Found on failure
/api/v1/find-recipe-by-no-of-servings | GET | Get all Recipes based on a specific number of servings | Request parameter Integer no-of-servings=<no-of-servings> | Recipe Model with 200 OK on Success, 401 Not Found on failure
/api/v1/search/findRecipeByIncludingIngredients | GET | Get all Recipes based on a specific ingredient | Request parameter String includes=<text> | Recipe Model with 200 OK on Success, 401 Not Found on failure
/api/v1/search/findRecipeByInstructionContainsText/{text} | GET | Get all Recipes based on a specific text in instruction | Path parameter String text | Recipe Model with 200 OK on Success, 401 Not Found on failure
/api/v1/search/findRecipeByExcludingIngredients | GET | Get all Recipes based on a specific ingredient | Request parameter Strin excludes=<text> | Recipe Model with 200 OK on Success, 401 Not Found on failure