package ee.aleksei.gvozdev.kafka;

import ee.aleksei.gvozdev.kafka.api.WageEvent;
import ee.aleksei.gvozdev.web.dto.WageCreateDto;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.util.Date;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
class KafkaPublisherTest {

    public static KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka").withTag("5.4.3"));

    static {
        kafka.start();
        System.setProperty("kafka.wages-topic.bootstrap-address", kafka.getBootstrapServers());
    }

    @Autowired
    private TestWageProcessor testWageProcessor;

    @Autowired
    private KafkaPublisher kafkaPublisher;

    @Autowired
    private KafkaTemplate<String, WageEvent> kafkaTemplate;

    @Test
    void publish() {
        Date date = new Date();
        WageCreateDto wageCreateDto = new WageCreateDto().setName("test").setSurname("test").setWage(BigDecimal.ONE).setEventTime(date);
        kafkaPublisher.publish(wageCreateDto);

        kafkaTemplate.flush();
        WageEvent expected = new WageEvent().setName("test").setSurname("test").setWage(BigDecimal.ONE).setEventTimeMillis(date.toInstant().toEpochMilli());

        await()
                .atLeast(Duration.ONE_HUNDRED_MILLISECONDS)
                .atMost(Duration.TEN_SECONDS)
                .with()
                .pollInterval(Duration.ONE_HUNDRED_MILLISECONDS)
                .until(testWageProcessor::getLastReceivedWageEvent, equalTo(expected));
    }
}
