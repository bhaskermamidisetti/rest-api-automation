# rest-api-automation
This is a sample project that uses the RestAssured library to perform API testing in Java Cucumber and Gradle as build tool. RestAssured is a high-level Java API testing library that simplifies and speeds up the process of integration and functionality testing of APIs.

## 📋 Prerequisites

Before running this project, make sure you have the following installed in your development environment:

* Java JDK 17.
* Gradle for dependency management.
* Cucumber - BDD/Gherkin style Feature files
* Rest assured - Rest api verification library

## ⚙️ Project Setup

Follow these steps to set up and run the project on your local machine:

1. Clone this repository to your local machine using the following command:
```sh
git clone https://github.com/bhaskermamidisetti/rest-api-automation.git
```
2. Navigate to the project's root directory:
```sh
cd rest-api-automation
```
3. Make sure you have all the project dependencies downloaded and updated by running the following command:
```sh
./gradlew clean build
```
4. Once all the dependencies have been downloaded and the project has been successfully built, you can execute the API & TestNG teststests using the following command:
```sh
./gradlew cucumber or ./gradlew test
```
5. Otherwise, you can execute the API & Cucumber tests using the following command:
```sh
./gradlew cucumber -Dcucumber.options="--tags @restapi"
```
This will run all the defined test cases in the project and display the results in the console.
