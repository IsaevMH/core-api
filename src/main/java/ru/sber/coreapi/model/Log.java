package ru.sber.coreapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.io.Serializable;
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
}