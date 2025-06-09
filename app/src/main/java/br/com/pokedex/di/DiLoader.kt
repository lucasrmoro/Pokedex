package br.com.pokedex.di

import br.com.pokedex.pokemons.PokemonsDI
import br.com.pokedex.pokemons.PokemonsDIImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val modules = module {
    single<PokemonsDI> { PokemonsDIImpl() }
}

fun loadModules() {
    loadKoinModules(modules)
}