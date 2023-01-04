package ru.sber.coreapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * SwaggerConfig.
 * Конфигурирует документирование endpoints программы.
 *
 * @author Maxim_Isaev.
 */
@Configuration
@Profile("swagger")
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi logApi() {
        return buildDocket("api", "/logs/**");
    }

    @Bean
    public GroupedOpenApi actuator() {
        return buildDocket("actuator", "/actuator/**");
    }

    protected GroupedOpenApi buildDocket(String url, String path) {
        return GroupedOpenApi.builder()
                .group(url)
                .pathsToMatch(path)
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Core API")
                        .description("Core API sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}