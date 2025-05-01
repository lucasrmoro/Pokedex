plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "br.com.pokedex.core"
}

dependencies {
    implementation(libs.bundles.android)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.koin)
}