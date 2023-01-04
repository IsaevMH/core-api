package ru.sber.coreapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * AbstractIntegrationTest.
 * Создает окружение для выполнения интеграционных тестов.
 *
 * @author Maxim_Isaev.
 */
@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ActiveProfiles(AbstractIntegrationTest.ACTIVE_PROFILE_TEST)
@ContextConfiguration(initializers = {AbstractIntegrationTest.Initializer.class})
public abstract class AbstractIntegrationTest {

    @Autowired
    protected ObjectMapper mapper;

    /**
     * Имя профиля test
     */
    public static final String ACTIVE_PROFILE_TEST = "test";

    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>("postgres:12.7")
            .withDatabaseName("auth_db")
            .withUsername("auth")
            .withPassword("auth-pswd");

    @TestConfiguration
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.liquibase.enabled=true"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    protected String contentToString(Object body) throws Exception {
        return getObjectMapper().writeValueAsString(body);
    }

    private ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}