# End-to-End Shuffle and Log Microservices

This project contains two microservices:
1. **service-shuffle**: Accepts a number from 1 to 1000, shuffles the numbers, and logs the request to another microservice.
2. **service-log**: Logs the request received from `service-shuffle` to the console.

### Prerequisites
- Java 17+
- Maven or Gradle

### Setup and Running the Services

1. Clone the repository and navigate to each service folder:
    - `cd service-shuffle`
    - `cd service-log`

2. Build both services using Maven:
    - `mvn clean install`

3. Run `service-log` on port `8081`:
    - `java -jar target/service-log-1.0.jar`

4. Run `service-shuffle` on port `8080`:
    - `java -jar target/service-shuffle-1.0.jar`

### Testing

- Send a POST request to shuffle numbers:
    - `curl --location 'http://localhost:8080/api/shuffle' \
            --header 'Content-Type: application/json' \
            --data '{
                    "number":5
                    }' `

- The response will be a shuffled array, and the request will be logged in the `service-log` console.

### Configuration
- You can configure the `service-log` URL in the `application.properties` of `service-shuffle`.

### Bonus
- Logging is done asynchronously using `@Async` to ensure minimal delay in the shuffle processing.


`Service Log API`
A Spring Boot application that provides a REST API to log shuffle requests.

Features
POST /api/logs endpoint to log shuffle requests containing an integer.
Validates the ShuffleRequest to ensure the number is not null.
Logs the request details using SLF4J.
Setup
Prerequisites
JDK 17+
Maven
Steps

Build the project:
`mvn clean install`

Run the application:
`mvn spring-boot:run`

`curl -X POST http://localhost:8080/api/logs \
-H "Content-Type: application/json" \
-d '{"number": 123}'`

response
`{ "number": 123 }
`
