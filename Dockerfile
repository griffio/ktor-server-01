FROM gradle:latest AS BUILD_STAGE
WORKDIR /tmp
COPY gradle gradle
COPY build.gradle.kts gradle.properties settings.gradle.kts gradlew ./
COPY src src
RUN ./gradlew --no-daemon buildFatJar

FROM amazoncorretto:25-alpine-jdk
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=BUILD_STAGE /tmp/build/libs/*-all.jar /app/ktor-server.jar
ENTRYPOINT ["java","--enable-native-access=ALL-UNNAMED","-Xlog:gc+init","-XX:+PrintCommandLineFlags","-jar","/app/ktor-server.jar"]
