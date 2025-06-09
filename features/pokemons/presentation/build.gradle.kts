import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.feature)
}

dependencies {
    implementation(project(Modules.Feature.Pokemons.DOMAIN))
    implementation(project(Modules.DiLoader.Pokemons.API))
    implementation(libs.flexbox)
}