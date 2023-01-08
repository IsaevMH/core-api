package ru.sber.coreapi.integration;

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

    @Test
    void addLogSuccess() throws Exception {
        var logDto = new LogDto()
                .setMessage("test")
                .setTime(Timestamp.from(Instant.now()))
                .setLevel("debug")
                .setType("system");

        mockMvc.perform(post("/logs")
                        .contentType(APPLICATION_JSON)
                        .content(contentToString(logDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

    @Test
    void addLogEmptyMessage() throws Exception {
        var logDto = new LogDto()
                .setMessage("")
                .setLevel("debug")
                .setType("system")
                .setTime(Timestamp.from(Instant.now()));

        mockMvc.perform(post("/logs")
                        .contentType(APPLICATION_JSON)
                        .content(contentToString(logDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.code", is("MethodArgumentNotValidException")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].fieldName", is("message")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].message", is("Field has to be filled")))
                .andExpect(jsonPath("$.systemName", is("core-api")))
                .andReturn().getResponse();
    }

    @Test
    void addLogNullType() throws Exception {
        var logDto = new LogDto()
                .setMessage("message")
                .setLevel("debug")
                .setTime(Timestamp.from(Instant.now()));

        mockMvc.perform(post("/logs")
                        .contentType(APPLICATION_JSON)
                        .content(contentToString(logDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.code", is("MethodArgumentNotValidException")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].fieldName", is("type")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].message", is("Field has to be filled")))
                .andExpect(jsonPath("$.systemName", is("core-api")))
                .andReturn().getResponse();
    }

    @Test
    void addLogNullLevel() throws Exception {
        var logDto = new LogDto()
                .setMessage("message")
                .setType("system")
                .setTime(Timestamp.from(Instant.now()));

        mockMvc.perform(post("/logs")
                        .contentType(APPLICATION_JSON)
                        .content(contentToString(logDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.code", is("MethodArgumentNotValidException")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].fieldName", is("level")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].message", is("Field has to be filled")))
                .andExpect(jsonPath("$.systemName", is("core-api")))
                .andReturn().getResponse();
    }

    @Test
    void addLogNullTime() throws Exception {
        var logDto = new LogDto()
                .setMessage("message")
                .setLevel("debug")
                .setType("system");

        mockMvc.perform(post("/logs")
                        .contentType(APPLICATION_JSON)
                        .content(contentToString(logDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.code", is("MethodArgumentNotValidException")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].fieldName", is("time")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].message", is("Field has to be filled")))
                .andExpect(jsonPath("$.systemName", is("core-api")))
                .andReturn().getResponse();
    }

    @Test
    void addLogLevelFieldIncorrectSize() throws Exception {
        var logDto = new LogDto()
                .setMessage("message")
                .setLevel("debug1")
                .setType("system")
                .setTime(Timestamp.from(Instant.now()));

        mockMvc.perform(post("/logs")
                        .contentType(APPLICATION_JSON)
                        .content(contentToString(logDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.code", is("MethodArgumentNotValidException")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].fieldName", is("level")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].message", is("The field size should be in the range of 1 to 5")))
                .andExpect(jsonPath("$.systemName", is("core-api")))
                .andReturn().getResponse();
    }

    @Test
    void addLogTypeFieldIncorrectSize() throws Exception {
        var logDto = new LogDto()
                .setMessage("message")
                .setLevel("debug")
                .setType("Some text for testing incorrect size case for type field")
                .setTime(Timestamp.from(Instant.now()));

        mockMvc.perform(post("/logs")
                        .contentType(APPLICATION_JSON)
                        .content(contentToString(logDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.code", is("MethodArgumentNotValidException")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].fieldName", is("type")))
                .andExpect(jsonPath("$.fieldValidationErrorDtos[0].message", is("The field size should be in the range of 1 to 30")))
                .andExpect(jsonPath("$.systemName", is("core-api")))
                .andReturn().getResponse();
    }
}