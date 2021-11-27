package ee.aleksei.gvozdev;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = { "kafka.wages-topic.bootstrap-address=${spring.embedded.kafka.brokers}" })
@EmbeddedKafka(topics = "${kafka.wages-topic.name}")
class PublisherApplicationTest {

    @Test
    void contextLoads() {

    }
}
