package ee.aleksei.gvozdev.kafka;

import ee.aleksei.gvozdev.kafka.api.WageEvent;
import ee.aleksei.gvozdev.kafka.api.WageEventSerde;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(WagesTopicPublisherProperties.class)
public class KafkaConfiguration {

    @Bean
    public KafkaAdmin kafkaAdmin(WagesTopicPublisherProperties wagesTopicPublisherProperties) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, wagesTopicPublisherProperties.getBootstrapAddress());
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic wagesTopic(WagesTopicPublisherProperties wagesTopicPublisherProperties) {
        return new NewTopic(wagesTopicPublisherProperties.getName(), wagesTopicPublisherProperties.getPartitions(), wagesTopicPublisherProperties.getReplicationFactor());
    }

    @Primary
    @Bean
    public WageEventSerde wageEventSerde() {
        return new WageEventSerde();
    }

    @Bean
    public ProducerFactory<String, WageEvent> producerFactory(WagesTopicPublisherProperties wagesTopicPublisherProperties, WageEventSerde wageEventSerde) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                wagesTopicPublisherProperties.getBootstrapAddress());
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                wageEventSerde.serializer().getClass());
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, WageEvent> kafkaTemplate(ProducerFactory<String, WageEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
