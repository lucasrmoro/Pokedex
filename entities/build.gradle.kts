import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.library)
}

android {
    namespace = "br.com.pokedex.entities"
}

dependencies {
    implementation(libs.gson)
    implementation(libs.bundles.room)
}