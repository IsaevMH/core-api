package ru.sber.coreapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Log.
 * Представление сущности Log в системе.
 *
 * @author Maxim_Isaev
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "logs")
@Accessors(chain = true)
public class Log {

    /**
     * Уникальный идентификатор лога
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(nullable = false)
    Long id;

    /**
     * Сообщение лога
     */
    @Column(nullable = false)
    String message;

    /**
     * Тип лога
     */
    @Column(nullable = false, length = 30)
    String type;

    /**
     * Уровень лога
     */
    @Column(nullable = false, length = 5)
    String level;

    /**
     * Время исполнения лога
     */
    @Column(nullable = false)
    Timestamp time;
}