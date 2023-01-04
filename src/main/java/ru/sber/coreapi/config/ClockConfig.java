package ru.sber.coreapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

/*
 * ClockConfig.
 * <p>Класс {@link ClockConfig} служит для конфигурирования экземпляра {@link Clock}
 * для дальнейшей работы с объектами классов, представляющими собой объекты даты-времени
 *
 * @author Maxim_Isaev
 */
@Configuration
public class ClockConfig {

    /*
     * Метод для создания настроенного экземпляра {@link Clock}
     *
     * @return {@link Clock}
     */
    @Bean
    public Clock clock() {
        return Clock.system(ZoneId.of("Europe/Moscow"));
    }
}