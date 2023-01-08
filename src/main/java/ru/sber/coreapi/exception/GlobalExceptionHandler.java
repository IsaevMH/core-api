package ru.sber.coreapi.exception;

import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sber.coreapi.dto.FieldValidationErrorDto;
import ru.sber.coreapi.dto.ResponseFieldValidationErrorDto;

/**
 * GlobalExceptionHandler.
 * Обрабатывает исключения в едином формате.
 *
 * @author Maxim_Isaev.
 */
@Slf4j
@RestControllerAdvice(basePackages = {"ru.sber.coreapi"})
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String systemName;

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseFieldValidationErrorDto> illegalArgumentException(MethodArgumentNotValidException exception) {
        log.error(exception.getLocalizedMessage(), exception);
        var errors = exception.getFieldErrors().stream()
                .map(error -> new FieldValidationErrorDto(error.getField(), error.getDefaultMessage()))
                .collect(toList());
        var response = new ResponseFieldValidationErrorDto(randomUUID(), "MethodArgumentNotValidException", systemName, errors);

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(response);
    }
}