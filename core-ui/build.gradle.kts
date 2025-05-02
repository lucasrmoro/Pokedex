import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.library)
}

android {
    namespace = "br.com.pokedex.core_ui"
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.Domain.POKEMONS))
    implementation(libs.glide)
    implementation(libs.palette)
    implementation(libs.bundles.android)
    implementation(libs.bundles.koin)
}