# Jar builder
FROM gradle:jdk21 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM eclipse-temurin:21-jre as runner
ARG POSTGRES_DB
ARG POSTGRES_USER
ARG POSTGRES_PASSWORD
ARG POSGRES_PORT
ARG POSTGRES_HOST
EXPOSE 8080
COPY --from=builder /home/gradle/src/build/libs/*.jar /app.jar
ENV POSTGRES_DB=${POSTGRES_DB}
ENV POSTGRES_USER=${POSTGRES_USER}
ENV POSTGRES_PASSWORD=${POSTGRES_PASSWORD}
ENV POSTGRES_PORT=${POSTGRES_PORT}
ENV POSTGRES_HOST=${POSTGRES_HOST}
# ENTRYPOINT [ "java", "-jar", "/app.jar" ]