import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.app)
}

quadrantConfig.generateByDefault = false

android {
    namespace = "br.com.pokedex"

    dynamicFeatures.apply {
        clear()
        addAll(listOf(Modules.Feature.POKEMONS))
    }
}

dependencies {
    implementation(project(Modules.CORE_NETWORK))
    implementation(project(Modules.LOCAL_STORAGE))
    implementation(project(Modules.Data.POKEMONS))
    implementation(libs.timber)
    implementation(libs.bundles.koin)
}