## Dev environment
 - Java 16
 - Maven
 - Docker

## How to run and test locally

1. `mvn clean install`
2. Start zookeeper, kafka, and consumer_app_db in docker-compose file
3. Run `PublisherApplication` and `ConsumerApplication` in IntelliJ
4. Go to [Swagger UI](http://localhost:8080/swagger-ui.html) page and post the wage
5. See saved result in database

## Issues

 - PublisherApplication connects to Kafka, but is not able to publish events: `org.apache.kafka.common.errors.TimeoutException: Expiring 2 record(s) for wages-local-0:120001 ms has passed since batch creation`
 