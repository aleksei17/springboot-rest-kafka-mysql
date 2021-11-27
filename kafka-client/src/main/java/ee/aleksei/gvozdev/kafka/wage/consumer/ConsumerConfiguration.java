package ee.aleksei.gvozdev.kafka.wage.consumer;

import ee.aleksei.gvozdev.kafka.api.WageEvent;
import ee.aleksei.gvozdev.kafka.api.WageEventSerde;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(WagesTopicConsumerProperties.class)
public class ConsumerConfiguration {

    @ConditionalOnMissingBean(WageEventSerde.class)
    @Bean
    public WageEventSerde wageEventSerde() {
        return new WageEventSerde();
    }

    @Bean
    public ConsumerFactory<String, WageEvent> wageConsumerFactory(WagesTopicConsumerProperties wagesTopicConsumerProperties, WageEventSerde wageEventSerde) {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                wagesTopicConsumerProperties.getBootstrapAddress());
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                wagesTopicConsumerProperties.getGroupId());
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                wageEventSerde.deserializer().getClass());
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                wageEventSerde.deserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, WageEvent> wageEventConcurrentKafkaListenerContainerFactory(ConsumerFactory<String, WageEvent> wageConsumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, WageEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(wageConsumerFactory);
        return factory;
    }
}
