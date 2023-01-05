FROM adoptopenjdk:11.0.3_7-jdk-hotspot

VOLUME /usr/app/logs
WORKDIR /usr/app
COPY ./build/libs/core-api-0.0.1.jar .
RUN chmod -R 777 /usr/app

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=local *.jar