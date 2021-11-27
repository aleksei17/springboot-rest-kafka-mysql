package ee.aleksei.gvozdev.kafka.api;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class WageEventSerde implements Serde<WageEvent> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final Serializer<WageEvent> SERIALIZER = new JsonSerializer<>(OBJECT_MAPPER);
    private static final Deserializer<WageEvent> DESERIALIZER = new JsonDeserializer<>(WageEvent.class, OBJECT_MAPPER)
            .trustedPackages("*");

    @Override
    public Serializer<WageEvent> serializer() {
        return SERIALIZER;
    }

    @Override
    public Deserializer<WageEvent> deserializer() {
        return DESERIALIZER;
    }
}
