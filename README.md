# GildedRose-refactoring-kata

## Build and Run project:

1. To build the project, in "GildedRose refactoring kata" directory: "mvn clean install"
2. To run the project, in "target" directory: "java -jar demo-0.0.1-SNAPSHOT.jar"
3. Open "Postman" (or WebBrowser)

## How to use:

1. To fetch all list of Items:
HTTP method GET

**http://localhost:8080/searchAll**

2.  For testing, to simulate how Items will change once updated every day (every day at midnight):
HTTP method GET

**http://localhost:8080/simulateItemChangesDayByDay**

## What's done:

1. Accomplished GildedRose refactoring kata in Java.
2. Upgraded item update algorithm to process items asynchronously.
3. Implementd GildedRose items Rest service, for items list endpoint and for testing, in Spring Boot.
4. Used MongoDB as database for items.
5. Implemented new feature to update items once a day every midnight.
6. Covered application with tests.
7. Used some Java 8 features like Lambda expressions, method references etc.