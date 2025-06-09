import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.app)
}

quadrantConfig.generateByDefault = false

dependencies {
    implementation(project(Modules.CORE_NETWORK))
    implementation(project(Modules.LOCAL_STORAGE))
    implementation(project(Modules.Feature.Pokemons.DATA))
    implementation(project(Modules.Feature.Pokemons.PRESENTATION))
    implementation(libs.timber)
    implementation(libs.bundles.koin)
}