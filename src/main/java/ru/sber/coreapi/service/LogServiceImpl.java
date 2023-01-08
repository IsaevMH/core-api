package ru.sber.coreapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.coreapi.config.LogMapper;
import ru.sber.coreapi.dto.LogDto;
import ru.sber.coreapi.repository.LogRepository;
import ru.sber.coreapi.util.DataWriterUtil;

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
    private final DataWriterUtil dataWriterUtil;

    @Override
    public void add(LogDto logDto) {
        log.info("Выполняется сохранение модели представления {}", logDto);
        Optional.of(logDto)
                .map(logMapper::logDtoToLog)
                .map(logRepository::save)
                .map(dataWriterUtil::write)
                .orElse(null);
    }
}