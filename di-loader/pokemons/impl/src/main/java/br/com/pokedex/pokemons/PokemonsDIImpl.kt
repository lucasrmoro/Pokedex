package br.com.pokedex.pokemons

import br.com.pokedex.pokemons.di.pokemonsDataModule
import br.com.pokedex.pokemons.di.pokemonsDomainModule
import br.com.pokedex.pokemons.di.pokemonsPresentationModules
import org.koin.core.context.loadKoinModules

class PokemonsDIImpl : PokemonsDI {

    override fun load() {
        loadKoinModules(
            listOf(pokemonsDataModule, pokemonsDomainModule).plus(pokemonsPresentationModules)
        )
    }

}