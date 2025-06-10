import br.com.pokedex.modules.Modules
import br.com.pokedex.ext.implementationAll
import br.com.pokedex.ext.projects
import br.com.pokedex.modules.Modules.DiLoader

plugins {
    alias(libs.plugins.pokedex.android.app)
}

dependencies {
    implementation(project(Modules.CORE_NETWORK))
    implementation(project(Modules.LOCAL_STORAGE))
    implementationAll(projects(DiLoader.allModules))
    implementation(project(Modules.Feature.Pokemons.PRESENTATION))
    implementation(libs.timber)
    implementation(libs.bundles.koin)
}