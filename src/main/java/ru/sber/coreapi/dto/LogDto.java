package ru.sber.coreapi.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * LogDto.
 * Модель представления для взаимодействия на уровне контроллера.
 *
 * @author Maxim_Isaev
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "Модель лога")
public class LogDto {

    @Schema(description = "Сообщение лога", example = "Формируется объект для...", requiredMode = REQUIRED)
    String message;

    @Schema(description = "Тип логирования", example = "system")
    String type;

    @Schema(description = "Уровень логирования", example = "debug")
    String level;

    @Schema(description = "Время исполнения события", type = "String", example = "2022-01-08 16:48:05.591")
    Timestamp time;
}