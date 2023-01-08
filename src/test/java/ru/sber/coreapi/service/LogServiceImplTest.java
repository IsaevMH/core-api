package ru.sber.coreapi.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
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

    InOrder inOrder;

    @BeforeEach
    void setUp() {
        inOrder = inOrder(subj, logRepository, logMapper);
    }

    @Test
    void addSuccess() {
        var logDto = new LogDto()
                .setMessage("test");
        var log = new Log()
                .setMessage(logDto.getMessage());

        doReturn(log).when(logMapper).logDtoToLog(logDto);
        doReturn(log).when(logRepository).save(log);

        assertDoesNotThrow(() -> subj.add(logDto));

        inOrder.verify(logMapper, times(1)).logDtoToLog(logDto);
        inOrder.verify(logRepository, times(1)).save(log);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void addEmptyLogMessage() {
        var logDto = new LogDto()
                .setMessage("");
        var log = new Log()
                .setMessage(logDto.getMessage());

        var expected = new IllegalArgumentException("Поле message не может быть пустым/null");

        var actual = assertThrows(IllegalArgumentException.class, () -> subj.add(logDto));

        assertEquals(expected.getLocalizedMessage(), actual.getLocalizedMessage());

        inOrder.verify(subj, times(1)).add(logDto);
        inOrder.verify(logMapper, never()).logDtoToLog(logDto);
        inOrder.verify(logRepository, never()).save(log);
        inOrder.verifyNoMoreInteractions();
    }
}