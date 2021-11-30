package ee.aleksei.gvozdev;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublisherApplicationTest {

    public static KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka").withTag("5.4.3"));

    static {
        kafka.start();
        System.setProperty("kafka.wages-topic.bootstrap-address", kafka.getBootstrapServers());
    }

    @Test
    void contextLoads() {

    }
}
