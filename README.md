## Dev environment
 - Java 16
 - Maven
 - Docker

## Flow

1. PublisherApplication has REST API for changing employee wages
2. When request received, PublisherApplication sends event to Kafka
3. ConsumerApplication reads event from Kafka and stores it to database

## How to run and test locally

1. `mvn clean install` (or _Build all_ in IntelliJ run configs)
2. `docker-compose -f docker-compose.local-dev.yml up -d` (or _Run docker-compose.local-dev_ in IntelliJ run configs)
3. Run `PublisherApplication` and `ConsumerApplication` in IntelliJ (run configs also provided)
4. Go to [Swagger UI](http://localhost:8080/swagger-ui.html) page and post the wage
5. See saved result in database

## Running dockerized apps

1. Stop containers for local development
2. `mvn clean install`
3. If a tester wants to run containers with their own variables, then values in `docker-compose.yml` and `init.sql` should be changed
4. `docker-compose up`
5. Go to [Swagger UI](http://localhost:8080/swagger-ui.html) page and post the wage
6. See saved result in database
