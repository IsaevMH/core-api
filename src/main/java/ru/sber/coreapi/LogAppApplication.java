package ru.sber.coreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"ru.sber.coreapi.repository"})
@SpringBootApplication
public class LogAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogAppApplication.class, args);
    }

}
