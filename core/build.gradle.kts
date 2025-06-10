plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.quadrant)
}

quadrantConfig.generateByDefault = false

dependencies {
    implementation(libs.bundles.android)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.koin)
}