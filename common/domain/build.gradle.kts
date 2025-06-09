import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.library)
}

dependencies {
    implementation(project(Modules.CORE))
}