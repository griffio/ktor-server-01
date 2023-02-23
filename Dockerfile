FROM gradle:latest AS BUILD_STAGE
COPY gradle gradle
COPY build.gradle.kts gradle.properties settings.gradle.kts gradlew ./
COPY src src
RUN ./gradlew buildFatJar

FROM openjdk:17-jdk-slim
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=BUILD_STAGE /build/libs/*-all.jar /app/ktor-server.jar
ENTRYPOINT ["java","-jar","/app/ktor-server.jar"]
