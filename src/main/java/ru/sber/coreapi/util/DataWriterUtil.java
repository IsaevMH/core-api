package ru.sber.coreapi.util;

import lombok.extern.slf4j.Slf4j;

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
public class DataWriterUtil {

    /**
     * Константа, содержащая путь до файла с логами.
     */
    public final static String FILE_NAME = "/usr/app/logs/log.txt";

    public static void save(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            log.info("Извините, не могу выполнить запись {} в файл", data);
        }
    }
}