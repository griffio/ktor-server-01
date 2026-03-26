plugins {
    application
    kotlin("jvm") version "2.3.10"
    id("io.ktor.plugin") version "3.4.1"
}

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

group = "griffio"
version = "0.0.2"

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
    implementation("io.ktor:ktor-server-routing-openapi:${ktorVersion}")
    implementation("io.ktor:ktor-server-openapi:${ktorVersion}")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}

ktor {
    openApi {
        enabled = true
        codeInferenceEnabled = true
        onlyCommented = false
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}
