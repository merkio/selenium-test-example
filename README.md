
## Selenium tests with testng, allure, maven
This project is an example how to build simple selenium tests with report
#### Preconditions
* maven > 3.5
* java 11
* chrome
#### Tests and Report
* Set in the tests.properties baseUrl and default user email and password for tests
* Launch tests: mvn clean -U test
* Build report: mvn allure:report or 
    if you want build report & open mvn allure:serve
