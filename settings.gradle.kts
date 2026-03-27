rootProject.name = "ktor-server-01"

dependencyResolutionManagement {

    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            plugin("kotlin", "org.jetbrains.kotlin.jvm").version("2.3.10")
            library("logbackVersion", "ch.qos.logback:logback-classic:1.5.32")
        }
        create("ktorLibs") {
            from("io.ktor:ktor-version-catalog:3.4.1")
        }
    }
}
