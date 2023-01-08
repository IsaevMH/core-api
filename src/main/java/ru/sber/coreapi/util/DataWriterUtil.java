package ru.sber.coreapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.sber.coreapi.model.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * DataWriterUtil.
 * Утилитарный класс для записи строки в файл.
 *
 * @author Maxim_Isaev.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataWriterUtil {

    private final ObjectMapper objectMapper;

    /**
     * Константа, содержащая путь до файла с логами.
     */
    public final static String FILE_NAME = "/usr/app/logs/log.txt";

    public Log write(Log journal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(journal));
            writer.newLine();
        } catch (IOException e) {
            log.info("Извините, не могу выполнить запись {} в файл", journal);
        }
        return journal;
    }
}