# Stage 1: Cache Gradle project dependencies
FROM gradle:9.4.1-jdk25 AS cache
WORKDIR /home/gradle/app
ENV GRADLE_USER_HOME=/home/gradle/.gradle

COPY gradlew build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle gradle
RUN chmod +x gradlew && ./gradlew --no-daemon dependencies

# Stage 2: Build project to distribution
FROM gradle:9.4.1-jdk25 AS build
ENV GRADLE_USER_HOME=/home/gradle/.gradle
COPY --from=cache /home/gradle/.gradle /home/gradle/.gradle

WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
RUN chmod +x gradlew && ./gradlew --no-daemon clean buildFatJar

# Stage 3: Runtime jar is copied from build
FROM amazoncorretto:25-alpine AS runtime

RUN addgroup -S appuser && adduser -S appuser -G appuser
USER appuser:appuser

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar /app/project.jar

EXPOSE 8080

ENV JAVA_OPTS="-Xlog:gc+init -XX:+PrintCommandLineFlags"
ENTRYPOINT ["java","-jar","--enable-native-access=ALL-UNNAMED","/app/project.jar"]
