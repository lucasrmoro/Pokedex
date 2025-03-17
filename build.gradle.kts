plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.jetbrains.jvm) apply false
    alias(libs.plugins.android.dynamic.feature) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}