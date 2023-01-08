package ru.sber.coreapi.service;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static ru.sber.coreapi.util.DataWriterUtil.save;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.coreapi.config.LogMapper;
import ru.sber.coreapi.dto.LogDto;
import ru.sber.coreapi.model.Log;
import ru.sber.coreapi.repository.LogRepository;

import java.util.Optional;

/**
 * LogServiceImpl.
 * Реализация {@link LogService} для выполнения операций над {@link LogDto}.
 *
 * @author Maxim_Isaev.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final LogMapper logMapper;

    @Override
    public void add(LogDto logDto) {
        log.info("Выполняется сохранение модели представления {}", logDto);
        Optional.of(logDto)
                .filter(dto -> isNotEmpty(dto.getMessage()))
                .map(logMapper::logDtoToLog)
                .map(logRepository::save)
                .map(this::saveToFile)
                .orElseThrow(() -> new IllegalArgumentException("Поле message не может быть пустым/null"));
    }

    /**
     * Сохраняет данные {@link Log} в файл.
     *
     * @param log сущность лога.
     * @return {@link Log}.
     */
    private Log saveToFile(Log log) {
        save(log.getMessage());
        return log;
    }
}