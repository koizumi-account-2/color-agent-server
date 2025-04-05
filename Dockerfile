# Stage 1: Build
FROM gradle:7 AS base
CMD ["bash"]

# test-and-build
FROM base AS test-and-build
COPY . /workspace
WORKDIR /workspace
RUN ./gradlew build -x test --info

# Stage 2: Runtime
FROM openjdk:17 AS production
COPY --from=test-and-build /workspace/build/libs/*.jar app.jar
CMD [ "java", "-jar", "api.jar" ]