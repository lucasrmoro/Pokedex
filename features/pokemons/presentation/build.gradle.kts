import br.com.pokedex.modules.Modules

plugins {
    alias(libs.plugins.pokedex.android.feature)
}

dependencies {
    implementation(project(Modules.Feature.Pokemons.DOMAIN))
    implementation(libs.flexbox)
}