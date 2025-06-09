import br.com.pokedex.ext.implementationAll
import br.com.pokedex.ext.projects
import br.com.pokedex.modules.Modules.DiLoader
import br.com.pokedex.modules.Modules.Feature.Pokemons.DATA
import br.com.pokedex.modules.Modules.Feature.Pokemons.DOMAIN
import br.com.pokedex.modules.Modules.Feature.Pokemons.PRESENTATION

plugins {
    alias(libs.plugins.pokedex.diLoader)
}

dependencies {
    implementationAll(projects(DiLoader.Pokemons.API, DATA, DOMAIN, PRESENTATION))
}