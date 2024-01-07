plugins {
    application
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.7"
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
    // add all tegralLibs to classpath to avoid module problem with `Supertypes of the following classes cannot be resolved. Please make sure you have the required dependencies in the classpath:
    // class guru.zoroark.tegral.openapi.dsl.MediaTypeBuilder, unresolved supertypes: guru.zoroark.tegral.core.Buildable`
    implementation(tegralLibs.core)
    implementation(tegralLibs.openapi.ktor)
    implementation(tegralLibs.openapi.dsl)
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

