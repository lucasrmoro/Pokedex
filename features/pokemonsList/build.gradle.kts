import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.feature)
}

android {
    namespace = "br.com.pokedex.pokemonsList"
}

dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.CORE_NETWORK))
    implementation(libs.bundles.retrofit)
}