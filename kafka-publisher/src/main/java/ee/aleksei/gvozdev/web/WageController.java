package ee.aleksei.gvozdev.web;

import ee.aleksei.gvozdev.kafka.KafkaPublisher;
import ee.aleksei.gvozdev.web.dto.WageCreateDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/wages")
public class WageController {

    private final KafkaPublisher kafkaPublisher;

    public WageController(KafkaPublisher kafkaPublisher) {
        this.kafkaPublisher = kafkaPublisher;
    }

    @PostMapping
    public void post(@Valid @RequestBody WageCreateDto wageCreateDto) {
        kafkaPublisher.publish(wageCreateDto);
    }
}
