dependencyResolutionManagement {
    versionCatalogs {
        repositories {
            google()
            mavenCentral()
        }
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}