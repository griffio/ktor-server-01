# Stage 1: Cache Gradle project dependencies
FROM gradle:9.4.1-jdk25 AS cache
WORKDIR /home/gradle/project_cache
ENV GRADLE_USER_HOME=/home/gradle/.gradle
COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts gradle.properties ./
RUN gradle --no-daemon dependencies

# Stage 2: Build project from src to jar
FROM gradle:9.4.1-jdk25 AS build
ENV GRADLE_USER_HOME=/home/gradle/.gradle
COPY --from=cache /home/gradle/.gradle /home/gradle/.gradle
COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts gradle.properties /home/gradle/project/
COPY --chown=gradle:gradle src /home/gradle/project/src
WORKDIR /home/gradle/project
RUN gradle --no-daemon buildFatJar

# Stage 3: Runtime jar is copied from build
FROM amazoncorretto:25-alpine AS runtime

RUN addgroup -S appuser && adduser -S appuser -G appuser
USER appuser:appuser

COPY --from=build /home/gradle/project/build/libs/*.jar /home/appuser/application.jar

# application working directory
WORKDIR /home/appuser

EXPOSE 8080

ENV JAVA_OPTS="-Xlog:gc+init -XX:+PrintCommandLineFlags"
ENTRYPOINT ["java","-jar","--enable-native-access=ALL-UNNAMED","/home/appuser/application.jar"]
