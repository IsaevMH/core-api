FROM adoptopenjdk:11.0.3_7-jdk-hotspot

WORKDIR /usr/app
COPY ./build/libs/*.jar .
RUN chmod -R 777 /usr/app

CMD java -jar /*.jar