import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.library)
}

android {
    namespace = "br.com.pokedex.core_data"
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.CORE_NETWORK))
    implementation(project(Modules.LOCAL_STORAGE))
    implementation(project(Modules.ENTITIES))
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.koin)
    compileOnly(libs.koin.compiler)
}