package ee.aleksei.gvozdev.kafka.wage.consumer;

import ee.aleksei.gvozdev.kafka.api.WageEvent;

public interface WageProcessor {

    void process(WageEvent wageEvent);
}
