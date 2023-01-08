package ru.sber.coreapi.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sber.coreapi.config.LogMapper;
import ru.sber.coreapi.dto.LogDto;
import ru.sber.coreapi.model.Log;
import ru.sber.coreapi.repository.LogRepository;
import ru.sber.coreapi.util.DataWriterUtil;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * LogServiceImplTest.
 * Проверяет методы класса {@link LogServiceImpl}.
 *
 * @author Maxim_Isaev.
 */
@ExtendWith(MockitoExtension.class)
class LogServiceImplTest {

    @Spy
    @InjectMocks
    LogServiceImpl subj;

    @Mock
    LogMapper logMapper;

    @Mock
    LogRepository logRepository;

    @Mock
    DataWriterUtil dataWriterUtil;

    InOrder inOrder;

    @BeforeEach
    void setUp() {
        inOrder = inOrder(subj, logRepository, logMapper, dataWriterUtil);
    }

    @Test
    void addSuccess() {
        var logDto = new LogDto()
                .setMessage("test")
                .setLevel("debug")
                .setType("system")
                .setTime(Timestamp.from(Instant.now()));
        var log = new Log()
                .setMessage(logDto.getMessage())
                .setLevel(logDto.getLevel())
                .setType(logDto.getType())
                .setTime(logDto.getTime());

        doReturn(log).when(logMapper).logDtoToLog(logDto);
        doReturn(log).when(logRepository).save(log);
        doReturn(log).when(dataWriterUtil).write(log);

        assertDoesNotThrow(() -> subj.add(logDto));

        inOrder.verify(logMapper, times(1)).logDtoToLog(logDto);
        inOrder.verify(logRepository, times(1)).save(log);
        inOrder.verify(dataWriterUtil, times(1)).write(log);
        inOrder.verifyNoMoreInteractions();
    }
}