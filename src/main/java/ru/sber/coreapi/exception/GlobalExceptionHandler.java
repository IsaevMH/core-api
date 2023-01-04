package ru.sber.coreapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sber.coreapi.dto.ErrorResponse;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpStatus.*;

/**
 * GlobalExceptionHandler.
 * Обрабатывает исключения и проводит к единому формату ответа.
 *
 * @author Maxim_Isaev.
 */
@Slf4j
@RestControllerAdvice(basePackages = {"ru.sber.coreapi"})
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String systemName;

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException exception) {
        log.error(exception.getLocalizedMessage(), exception);
        var error = new ErrorResponse(
                randomUUID(),
                "illegalArgumentException",
                exception.getLocalizedMessage(),
                systemName
        );
        return new ResponseEntity<>(error, new HttpHeaders(), BAD_REQUEST);
    }
}