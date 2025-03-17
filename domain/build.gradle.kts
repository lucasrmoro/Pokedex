import br.com.pokedex.ext.ksp
import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "br.com.pokedex.domain"
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.DATA))
    implementation(project(Modules.ENTITIES))
    implementation(project(Modules.CORE_UI))
    implementation(libs.coroutines)
    implementation(libs.bundles.koin)
    ksp(libs.koin.compiler)
}