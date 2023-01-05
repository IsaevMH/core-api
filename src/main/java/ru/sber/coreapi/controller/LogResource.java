package ru.sber.coreapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.coreapi.dto.LogDto;

/**
 * LogResource.
 * API для работы с логами.
 *
 * @author Maxim_Isaev
 */
@Tag(name = "API для работы с логами")
@RequestMapping("/logs")
public interface LogResource {

    @Operation(description = "Новая информация о логе.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лог успешно добавлен."),
    })
    @PostMapping
    ResponseEntity<LogDto> add(@RequestBody LogDto logDto);
}