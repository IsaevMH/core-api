package ru.sber.coreapi.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * FieldValidationErrorDto.
 * Модель представления ошибки валидации поля.
 *
 * @author Maxim_Isaev.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Модель ошибки валидации поля")
public class FieldValidationErrorDto {

    @Schema(description = "Наименование поля", example = "type", requiredMode = REQUIRED)
    private String fieldName;

    @Schema(description = "Сообщение ошибки", example = "Поле не может быть пустым/null", requiredMode = REQUIRED)
    private String message;
}