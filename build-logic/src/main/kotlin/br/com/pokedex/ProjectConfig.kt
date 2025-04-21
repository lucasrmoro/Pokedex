package br.com.pokedex

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object ProjectConfig {
    const val APP_ID = "br.com.pokedex"
    const val NAMESPACE = "br.com.pokedex"
    const val MIN_SDK = 26
    const val TARGET_SDK = 35
    const val COMPILE_SDK = 35
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val ANDROID_JUNIT_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    val JVM_TARGET = JvmTarget.JVM_17
    val JAVA_VERSION = JavaVersion.VERSION_17
    const val PROGUARD_ANDROID_OPTIMIZE = "proguard-android-optimize.txt"
    const val PROGUARD_RULES = "proguard-rules.pro"
}

internal object Plugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val ANDROID_DYNAMIC_FEATURE = "com.android.dynamic-feature"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KSP = "com.google.devtools.ksp"
    const val QUADRANT = "com.gaelmarhic.quadrant"
}

internal object Flavors {
    const val DEV = "dev"
    const val PRD = "prd"
    const val DEBUG_APP_ID_SUFFIX = ".debug"
    const val DEBUG_APP_VERSION_NAME_SUFFIX = "-debug"
    const val DEFAULT_DIMEN = "default"
}

internal object BuildTypes {
    const val DEBUG = "debug"
    const val RELEASE = "release"
}

internal object ConfigField {
    const val API_BASE_URL = "API_BASE_URL"
    const val API_BASE_URL_PRD = "https://pokeapi.co/api/v2/"
    const val API_BASE_URL_DEV = "https://pokeapi.co/api/v2/"
}

internal object Signing {
    const val KEYSTORE_FILE_NAME = "keystore.jks"
    const val KEYSTORE_FILE_PASSWORD = "KEYSTORE_FILE_PASSWORD"
    const val KEY_ALIAS = "KEY_ALIAS"
    const val KEY_PASSWORD = "KEY_PASSWORD"
}