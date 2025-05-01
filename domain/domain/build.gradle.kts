import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.library)
}

android {
    namespace = "br.com.domain"
}

dependencies {
    implementation(project(Modules.CORE))
}