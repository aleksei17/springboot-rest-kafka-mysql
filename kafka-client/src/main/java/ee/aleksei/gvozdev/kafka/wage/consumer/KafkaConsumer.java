package ee.aleksei.gvozdev.kafka.wage.consumer;

import ee.aleksei.gvozdev.kafka.api.WageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final WageProcessor wageProcessor;

    public KafkaConsumer(WageProcessor wageProcessor) {
        this.wageProcessor = wageProcessor;
    }

    @KafkaListener(
            topics = "${kafka.wages-topic.name}",
            containerFactory = "wageEventConcurrentKafkaListenerContainerFactory")
    public void consumeWage(WageEvent wageEvent) {
        log.info("Wage event received: " + wageEvent);
        wageProcessor.process(wageEvent);
    }
}
