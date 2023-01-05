## Инструкция по запуску.

* Склонировать репозиторий командой:  ```git clone git@github.com:IsaevMH/core-api.git ```;
* Открыть приложение в Idea;
* Запустить команду чистки/сборки проекта `./gradlew clean assemble`
* Выполнить команду ```docker-compose up -d```;
  * Для посмотра файла с сообщениями логов, необходимо раскомментировать с **22 по 23** строчки в файле `docker-compose`.
  * Заменить путь до знака ':' на свой, в строчке 23 файла docker-compose.
* Вставить в браузер следующий url: http://localhost/core-api/swagger-ui/index.html;
* Отправить запрос.

**Заметка**. Проверить присутсвие приложений docker и docker-compose. В случае их отсутствия воспользоватся гайдами по установки:
1. Для docker: https://docs.docker.com/engine/install/
2. Для docker-compose: https://docs.docker.com/compose/install/