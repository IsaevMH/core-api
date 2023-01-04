package ru.sber.coreapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.coreapi.dto.LogDto;

/**
 * LogResourceImpl.
 * Реализация {@link LogResource} для работы с логами.
 *
 * @author Maxim_Isaev.
 */
@Slf4j
@RestController
public class LogResourceImpl implements LogResource {

    @Override
    public ResponseEntity<LogDto> add(LogDto logDto) {
        return null;
    }
}
