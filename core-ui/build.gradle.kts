import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.library)
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.COMMON_DOMAIN))
    implementation(project(Modules.Feature.Pokemons.DOMAIN))
    implementation(libs.glide)
    implementation(libs.palette)
    implementation(libs.bundles.android)
    implementation(libs.bundles.koin)
    implementation(libs.skeletonLayout)
}