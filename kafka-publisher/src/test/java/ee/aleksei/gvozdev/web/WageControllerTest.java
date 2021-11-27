package ee.aleksei.gvozdev.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.aleksei.gvozdev.kafka.KafkaPublisher;
import ee.aleksei.gvozdev.web.dto.WageCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WageController.class)
class WageControllerTest {

    private static final String ENDPOINT = "/wages";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private KafkaPublisher kafkaPublisher;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void postTest() throws Exception {

        WageCreateDto wageCreateDto = new WageCreateDto()
                .setName("name")
                .setSurname("surname")
                .setWage(BigDecimal.ONE)
                .setEventTime(new Date());

        String request = objectMapper.writeValueAsString(wageCreateDto);

        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        verify(kafkaPublisher).publish(any());
    }
}
