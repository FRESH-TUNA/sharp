# BUILDER
FROM gradle:7.6.1-jdk17 AS builder
ADD ./ /sharp
WORKDIR /sharp
RUN gradle :api:inventory-api:bootJar --no-daemon

# RUNNING
FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /sharp
COPY --from=builder /sharp/api/inventory-api/build/libs/inventory-api-0.0.1-SNAPSHOT.jar inventory.jar
CMD java -Dspring.profiles.active=default -Dserver.port=$PORT $JAVA_OPTS -Dspring.config.location=application.yaml -jar inventory.jar

