package ru.sber.coreapi.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * DataWriterUtil.
 * Утилитарный класс для записи строки в файл.
 *
 * @author Maxim_Isaev.
 */
@Slf4j
public class DataWriterUtil {

    /**
     * Константа, содержащая наименование файла.
     */
    public final static String FILE_NAME = "log.txt";

    public static void save(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            log.info("Извините, не могу выполнить запись {} в файл", data);
        }
    }
}