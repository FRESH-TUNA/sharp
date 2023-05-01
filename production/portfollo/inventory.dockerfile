# BUILDER
FROM openjdk:17 AS builder
WORKDIR /sharp
RUN ./gradlew :api:inventory-api:bootJar

# RUNNING
FROM openjdk:17
WORKDIR /sharp
COPY --from=builder /api/inventory-api/build/libs/inventory-api-0.0.1-SNAPSHOT.jar /inventory.jar
CMD java -Dspring.profiles.active=prod -Dserver.port=$PORT $JAVA_OPTS -Dspring.config.location=/application.yaml -jar /inventory.jar
