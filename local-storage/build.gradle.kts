import br.com.pokedex.modules.Modules
import br.com.pokedex.ext.ksp

plugins {
    alias(libs.plugins.pokedex.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "br.com.pokedex.local_storage"
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.ENTITIES))
    implementation(libs.androidx.security)
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    implementation(libs.bundles.koin)
    ksp(libs.koin.compiler)
}