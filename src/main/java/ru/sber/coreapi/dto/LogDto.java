package ru.sber.coreapi.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * LogDto.
 * Модель представления для взаимодействия на уровне контроллера.
 *
 * @author Maxim_Isaev
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "Модель лога")
public class LogDto {

    @Schema(description = "Сообщение лога", example = "Формируется объект для...", requiredMode = REQUIRED)
    @NotBlank(message = "Field has to be filled")
    String message;

    @Schema(description = "Тип логирования", example = "system")
    @NotBlank(message = "Field has to be filled")
    @Size(min = 1, max = 30, message = "The field size should be in the range of 1 to 30")
    String type;

    @Schema(description = "Уровень логирования", example = "debug")
    @NotBlank(message = "Field has to be filled")
    @Size(min = 1, max = 5, message = "The field size should be in the range of 1 to 5")
    String level;

    @Schema(description = "Время исполнения события", type = "String", example = "2022-01-08 16:48:05.591")
    @NotNull(message = "Field has to be filled")
    Timestamp time;
}