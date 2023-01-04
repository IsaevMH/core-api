package ru.sber.coreapi.service;

import ru.sber.coreapi.dto.LogDto;

/**
 * LogService.
 * Отвечает за выполнение операций с представлением {@link LogDto}.
 *
 * @author Maxim_Isaev.
 */
public interface LogService {

    /**
     * Добавление нового лога.
     *
     * @param logDto представление лога.
     * @return {@link LogDto}.
     */
    LogDto add(LogDto logDto);
}