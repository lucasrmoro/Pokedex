import br.com.pokedex.ProjectConfig
import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.app)
}

quadrantConfig.generateByDefault = false

android {
    namespace = ProjectConfig.NAMESPACE

    dynamicFeatures.apply {
        clear()
        addAll(listOf(Modules.POKEMONS_LIST))
    }
}

dependencies {
    implementation(project(Modules.CORE_NETWORK))
    implementation(project(Modules.LOCAL_STORAGE))
    implementation(project(Modules.DATA))
    implementation(project(Modules.DOMAIN))
    implementation(libs.timber)
    implementation(libs.bundles.koin)
}