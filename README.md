[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/merkushevio/selenium-test-example.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/merkushevio/selenium-test-example/context:java)
## Selenium tests with junit5, allure, maven
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
