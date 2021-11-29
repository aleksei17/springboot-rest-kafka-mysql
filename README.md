## Dev environment
 - Java 16
 - Maven
 - Docker

## Flow

1. PublisherApplication has REST API for changing employee wages
2. When request received, PublisherApplication sends event to Kafka
3. ConsumerApplication reads event from Kafka and stores it to database

## How to run and test locally

1. `mvn clean install`
2. Start zookeeper, kafka_local_dev, and consumer_app_db in docker-compose file
3. Run `PublisherApplication` and `ConsumerApplication` in IntelliJ
4. Go to [Swagger UI](http://localhost:8080/swagger-ui.html) page and post the wage
5. See saved result in database

## Running dockerized apps

1. `mvn clean install`
2. Start zookeeper, kafka, and consumer_app_db in docker-compose file
3. Start publisher_app and consumer_app in docker-compose file
4. Go to [Swagger UI](http://localhost:8080/swagger-ui.html) page and post the wage
5. See saved result in database
