package ru.sber.coreapi.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * ErrorResponse.
 * Модель представления исключительной ситуаци возникающей при некорректном запросе.
 *
 * @author Maxim_Isaev.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Модель ошибки")
public class ErrorResponse {

    @Schema(description = "Уникальный идентификатор ошибки", example = "1L", requiredMode = REQUIRED)
    private UUID id;

    @Schema(description = "Код ошибки", example = "IllegalArgumentException", requiredMode = REQUIRED)
    private String code;

    @Schema(description = "Сообщение ошибки", example = "DTO не соответствует требованиям", requiredMode = REQUIRED)
    private String message;

    @Schema(description = "Наименование системы", example = "core-api", requiredMode = REQUIRED)
    private String systemName;
}