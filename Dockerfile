FROM adoptopenjdk/openjdk13:jdk-13.0.2_8-alpine-slim

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
COPY .env .

RUN chmod +x ./gradlew
ENTRYPOINT ["/gradlew", "bootRun","-Dspring.profiles.active=core,deploy"]
