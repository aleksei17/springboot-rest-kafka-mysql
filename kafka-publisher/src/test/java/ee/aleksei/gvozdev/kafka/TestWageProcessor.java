package ee.aleksei.gvozdev.kafka;

import ee.aleksei.gvozdev.kafka.api.WageEvent;
import ee.aleksei.gvozdev.kafka.wage.consumer.WageProcessor;
import org.springframework.stereotype.Component;

@Component
public class TestWageProcessor implements WageProcessor {

    private WageEvent lastReceivedWageEvent;

    @Override
    public void process(WageEvent wageEvent) {
        lastReceivedWageEvent = wageEvent;
    }

    public WageEvent getLastReceivedWageEvent() {
        return lastReceivedWageEvent;
    }
}
