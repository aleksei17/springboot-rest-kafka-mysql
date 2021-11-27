package ee.aleksei.gvozdev.kafka.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WageEventSerdeTest {

    private final WageEventSerde wageEventSerde = new WageEventSerde();

    @Test
    void serde() {
        WageEvent initial = new WageEvent().setName("test").setSurname("test").setWage(BigDecimal.ONE).setEventTimeMillis(1L);
        byte[] serialized = wageEventSerde.serializer().serialize("test", initial);
        WageEvent deserialized = wageEventSerde.deserializer().deserialize("test", serialized);

        assertEquals(initial, deserialized);
    }

    @DisplayName("Check that existing events in topic can be deserialized")
    @Test
    void deserializer() {

        // language=json
        String json = """
                {
                    "UNKNOWN": "unknown",
                    "name": "test",
                    "surname": "test",
                    "wage": 1,
                    "eventTimeMillis": 1
                }
                """;
        WageEvent expected = new WageEvent().setName("test").setSurname("test").setWage(BigDecimal.ONE).setEventTimeMillis(1L);
        WageEvent actual = wageEventSerde.deserializer().deserialize("test", json.getBytes());

        assertEquals(expected, actual);
    }
}
