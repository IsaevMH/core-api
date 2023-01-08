package ru.sber.coreapi.model;

import static javax.persistence.GenerationType.IDENTITY;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

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
public class Log implements Serializable {

    private static final long serialVersionUID = -5957113287409375392L;

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