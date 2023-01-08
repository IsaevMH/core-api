package ru.sber.coreapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.coreapi.dto.LogDto;
import ru.sber.coreapi.service.LogService;

/**
 * LogResourceImpl.
 * Реализация {@link LogResource} для работы с логами.
 *
 * @author Maxim_Isaev.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class LogResourceImpl implements LogResource {

    private final LogService logService;

    @Override
    public void add(LogDto logDto) {
        log.info("add with {} - start", logDto);
        logService.add(logDto);
        log.info("add with {} - end", logDto);
    }
}
