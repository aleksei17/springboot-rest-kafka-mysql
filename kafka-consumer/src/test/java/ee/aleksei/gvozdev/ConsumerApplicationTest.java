package ee.aleksei.gvozdev;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(topics = "${kafka.wages-topic.name}")
class ConsumerApplicationTest {

    @Test
    void contextLoads() {

    }
}
