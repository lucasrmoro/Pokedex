import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.data)
}

android {
    namespace = "br.com.pokedex.data_pokemons"
}

dependencies {
    implementation(project(Modules.Domain.POKEMONS))
}