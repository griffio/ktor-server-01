plugins {
    application
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.3"
}

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

group = "griffio"
version = "0.0.1"

application {
    mainClass.set("griffio.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

ktor {
    docker {
        jreVersion.set(io.ktor.plugin.features.JreVersion.JRE_17)
        externalRegistry.set(
            io.ktor.plugin.features.DockerImageRegistry.dockerHub(
                appName = provider { "ktor-app" },
                username = providers.environmentVariable("DOCKER_HUB_USERNAME"),
                password = providers.environmentVariable("DOCKER_HUB_PASSWORD")
            )
        )
    }
}

