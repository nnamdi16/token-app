# TOKEN APP
Welcome to the Token App!. The token app is a web based application that allows a user to generate and validate tokens
using the [Luhn's algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm ). This repository consists of two backend services (the generator service and the validator service)
built with Spring Boot and a UI built with React.

## Table of Contents
* Services
* Getting Started
  * Prerequisites
  * Running the application
* Running Test
* Swagger Documentation
* Assumptions


## Services

### Generator Service
The Generator Service is responsible for generating tokens based on a set of available digits.

### Validator Service
The Validator Service validates tokens generated by the Generator Service using the Luhn algorithm.

### User Interface
The User interface is used by the client to generate a token and validate token.


## Getting Started

### Prerequisite
- Java Development Kit [(JDK)](https://www.oracle.com/java/technologies/downloads/) 17 or higher
- Node.js: Make sure you have Node.js installed on your machine. You can download it from [nodejs.org](https://nodejs.org/en) preferably the LTS version .
- Apache Maven 3.6.0 or higher
- npm (Node Package Manager): This comes bundled with Node.js, so there's no need to install it separately
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=mac) with the Spring Boot plugin or any other suitable IDE that can run spring boot
- Web Browser: For testing and running the User interface application, you'll need a modern web browser like Google Chrome, Mozilla Firefox, or Microsoft Edge.


### Running the application
- Clone the [repository](https://github.com/nnamdi16/token-app.git)
- In the frontend folder, run the following commands below:
```bash 
cd frontend
npm install
 ```
- There is a .env.example file in the front end folder. Create your own copy and name it .env 
so that the react application can access the environment variables.
- To run the entire application, run the command below in the root directory of the project based on you operating system (OS)

```bash
For mac/linux
./start.sh

For Windows
./start.bat
```
NB: By default the frontend runs on port 8080, the generator service runs on port 8081
while the validator service runs on port 8082. You can modify these ports in the 
application.properties file of each of the services while for the frontend in the package.json file.



## Running Test
#### Spring Boot Application
To run the test for the spring boot application, run the command below:

```bash
mvn clean test
```

To generate test coverage report for the spring boot application, run the command below:

For generator service:
```bash
cd generator
mvn jacoco:report
```

For Validator service:
```bash
cd validator
mvn jacoco:report
```
To view the report you need to run the report which is an index.html file in the target/site/jacoco folder in a browser.

#### React Application

To run test in the react application, run the command below:
```bash
cd frontend
npm run test
```

To generate test coverage report for the react app, run the command below:

```bash
cd frontend
npx jest --coverage
```
To view the report you need to run the report which is an index.html file in the coverage/Icov-report folder in a browser.

## Documentation
The REST endpoint for both the generator service and the validator services are documented using swagger.
The swagger documentation UI is seen below:
- [Generator Service - http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- [Validator Service - http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

## Assumptions
- The available number of digits (pin) is 5.
- The token generated is 16 digits with the format XXXX-XXXX-XXXX-XXXX

