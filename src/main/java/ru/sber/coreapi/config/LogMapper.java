package ru.sber.coreapi.config;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sber.coreapi.dto.LogDto;
import ru.sber.coreapi.model.Log;

/**
 * LogMapper.
 * Преобразует {@link LogDto} в {@link Log}.
 *
 * @author Maxim_Isaev.
 */
@Mapper(componentModel = "spring")
public interface LogMapper {

    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);

    @Mapping(source = "message", target = "message")
    Log logDtoToLog(LogDto logDto);
}