# API Users

This README provides instructions on how to test the API Users Spring Boot application.

## Table of Contents

1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Setup](#setup)
4. [Testing the API](#testing-the-api)
5. [Swagger Documentation](#swagger-documentation)

## Introduction

API Users is a Spring Boot application that This Spring Boot project implements user CRUD,
you can create, list and delete users. Persistence is done through an H2 database and has Swagger
documentation implemented for easy API exploration and testing.

## Prerequisites

Before you begin testing the application, ensure you have the following installed:

- Java Development Kit (JDK) 17 or higher
- Apache Maven
- Postman, any API testing tool or you can test the API from the swagger UI

## Setup

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/sebas92091/nisum-test
   ```
2. Navigate to the project directory:
   
    ```bash
    cd <project-directory>
    ```

3. Build the application using Maven:
    ```bash
    mvn clean install
    ```
4. To run only the tests:
    ```bash
    mvn test
    ```

5. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Testing The API
  After running the application, to test the API you can go to the Swagger documentation at the link http://localhost:8080/swagger-ui/index.html,
  there you will find the API enpoints and the data required to test each one.

  When you are in the swagger interface, remember to do the following steps to configure the JWT token for the creation of a user.

  1. Click on the authorize button as shown in the image.
  <div align="center">
    <a href="./">
        <img src="./figures/swagger 1.png" width="79%"/>
    </a>
  </div>
  
  2. Paste the JWT token in the Value field, and click the authorize button. Ready, the JWT token is configured when the user creation endpoint is executed.
  <div align="center">
    <a href="./">
        <img src="./figures/swagger 2.png" width="79%"/>
    </a>
  </div>

  
  ### Create user
  To create a user you must keep in mind:
  1. name field, cannot be null or blank
  2. email field, it cannot be null and must follow the pattern aaaaaaa@dominio.cl
  3. password field, initially the project has the following requirements configured for it:  

     - minimum 10 characters
     - minimum 1 number
     - minimum 1 capital letter
    
     The requirements of the password field are configurable, they can be changed by establishing a regular expression with
     the desired requirements by changing the value of the **validation.password.regex** variable in the **appiclation.properties**
     of the project.
  
## Swagger Documentation
  The API endpoints are documented using Swagger. After running the application, you can access the Swagger UI at: http://localhost:8080/swagger-ui/index.html



