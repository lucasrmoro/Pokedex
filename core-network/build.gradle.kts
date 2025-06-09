import br.com.pokedex.ext.ksp
import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.LOCAL_STORAGE))
    implementation(libs.bundles.retrofit)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.coroutines)
    implementation(libs.bundles.koin)
    ksp(libs.koin.compiler)
}