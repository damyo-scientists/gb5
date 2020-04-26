FROM gradle:6.0.1-jdk11

COPY build.gradle .
COPY settings.gradle .
COPY src src
COPY .env .

ENTRYPOINT ["gradle", "bootRun","-Dspring.profiles.active=core,deploy"]
