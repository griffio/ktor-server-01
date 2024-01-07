rootProject.name = "ktor-server-01"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("tegralLibs") {
            from("guru.zoroark.tegral:tegral-catalog:0.0.4")
        }
    }
}
