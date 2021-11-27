package ee.aleksei.gvozdev.kafka;

import ee.aleksei.gvozdev.kafka.api.WageEvent;
import ee.aleksei.gvozdev.web.dto.WageCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final KafkaTemplate<String, WageEvent> kafkaTemplate;
    private final WagesTopicPublisherProperties wagesTopicPublisherProperties;

    public KafkaPublisher(
            KafkaTemplate<String, WageEvent> kafkaTemplate,
            WagesTopicPublisherProperties wagesTopicPublisherProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.wagesTopicPublisherProperties = wagesTopicPublisherProperties;
    }

    public void publish(WageCreateDto wageCreateDto) {
        log.info("received wageCreateDto: {}", wageCreateDto);
        WageEvent wageEvent = map(wageCreateDto);
        kafkaTemplate.send(wagesTopicPublisherProperties.getName(), wageEvent.getKey(), wageEvent).completable().whenComplete((result, throwable) -> {
            if (throwable == null) {
                log.info("Wage event sent successfully: {}", result);
            } else {
                log.error("Could not send event: " + wageEvent, throwable);
            }
        });
    }

    private WageEvent map(WageCreateDto wageCreateDto) {
        return new WageEvent()
                .setName(wageCreateDto.getName())
                .setSurname(wageCreateDto.getSurname())
                .setWage(wageCreateDto.getWage())
                .setEventTimeMillis(wageCreateDto.getEventTime().toInstant().toEpochMilli());
    }
}
