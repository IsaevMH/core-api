package ru.sber.coreapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sber.coreapi.model.Log;

/**
 * LogRepository.
 * Интерфейс для работы с сущностью {@link Log} в БД.
 *
 * @author Maxim_Isaev
 */
@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
}