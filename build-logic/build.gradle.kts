plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.quadrant)
    compileOnly(libs.android.gradle.tools)
    compileOnly(libs.kotlin.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApp") {
            id = libs.plugins.pokedex.android.app.get().pluginId
            implementationClass = "br.com.pokedex.plugins.AndroidAppPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.pokedex.android.library.get().pluginId
            implementationClass = "br.com.pokedex.plugins.AndroidLibraryPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.pokedex.android.feature.get().pluginId
            implementationClass = "br.com.pokedex.plugins.AndroidFeaturePlugin"
        }
    }
}