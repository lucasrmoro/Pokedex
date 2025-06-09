import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.data)
}

dependencies {
    implementation(project(Modules.Feature.Pokemons.DOMAIN))
}