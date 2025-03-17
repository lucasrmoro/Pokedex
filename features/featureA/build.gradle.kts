import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.feature)
}

android {
    namespace = "br.com.pokedex.featurea"
}

dependencies {
    implementation(project(Modules.DOMAIN))
}