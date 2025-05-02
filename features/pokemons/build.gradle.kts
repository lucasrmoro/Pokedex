import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.feature)
}

android {
    namespace = "br.com.pokedex.pokemons"
}

dependencies {
    implementation(project(Modules.Domain.POKEMONS))
    implementation(libs.flexbox)
}