# ===== Stage 1: Build =====
FROM gradle:8.5-jdk21 AS build

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle.properties ./
COPY gradle ./gradle
COPY gradlew gradlew.bat ./

RUN gradle dependencies --no-daemon

COPY src ./src

RUN gradle build -x test --no-daemon

# ===== Stage 2: Runtime =====
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
