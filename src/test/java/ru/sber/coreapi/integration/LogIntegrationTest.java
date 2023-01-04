package ru.sber.coreapi.integration;

import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import ru.sber.coreapi.dto.LogDto;
import ru.sber.coreapi.repository.LogRepository;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;

/**
 * LogIntegrationTest.
 * Осущесвтяет сквозную проверку операций над {@link LogDto}.
 *
 * @author Maxim_Isaev.
 */
class LogIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LogRepository logRepository;

    @Autowired
    Clock clock;

    @Test
    void addLogSuccess() throws Exception {
        var logDto = new LogDto()
                .setMessage("test")
                .setTime(Timestamp.from(Instant.now(clock)))
                .setLevel("debug")
                .setType("system");

        mockMvc.perform(post("/logs")
                        .contentType(APPLICATION_JSON)
                        .content(contentToString(logDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(logDto.getMessage())))
                .andExpect(jsonPath("$.type", is(logDto.getType())))
                .andExpect(jsonPath("$.level", is(logDto.getLevel())))
                .andReturn().getResponse();
    }

    @Test
    void addLogEmptyMessage() throws Exception {
        var logDto = new LogDto()
                .setMessage(EMPTY)
                .setTime(Timestamp.from(Instant.now(clock)))
                .setLevel("debug")
                .setType("system");

        mockMvc.perform(post("/logs")
                        .contentType(APPLICATION_JSON)
                        .content(contentToString(logDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.code", is("illegalArgumentException")))
                .andExpect(jsonPath("$.message", is("Поле message не может быть пустым/null")))
                .andExpect(jsonPath("$.systemName", is("core-api")))
                .andReturn().getResponse();
    }
}