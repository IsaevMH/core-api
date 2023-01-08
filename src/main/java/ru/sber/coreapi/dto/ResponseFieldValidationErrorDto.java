package ru.sber.coreapi.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

/**
 * ResponseFieldValidationErrorDto.
 * Модель представления списка ошибок валидации полей
 *
 * @author Maxim_Isaev.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Модель списка ошибок валидации полей")
public class ResponseFieldValidationErrorDto {

    @Schema(description = "Уникальный идентификатор ошибки", example = "1L", requiredMode = REQUIRED)
    private UUID id;

    @Schema(description = "Код ошибки", example = "IllegalArgumentException", requiredMode = REQUIRED)
    private String code;

    @Schema(description = "Наименование системы", example = "core-api", requiredMode = REQUIRED)
    private String systemName;

    @Schema(description = "Список ошибок валидации полей", requiredMode = REQUIRED)
    private List<FieldValidationErrorDto> fieldValidationErrorDtos;
}
