# Stage 1: Build
FROM gradle:8.4-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build -x test

# Stage 2: Runtime
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]