package br.com.pokedex.di

import br.com.pokedex.pokemons.PokemonsFeatureDI
import br.com.pokedex.pokemons.PokemonsFeatureDIImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val modules = module {
    single<PokemonsFeatureDI> { PokemonsFeatureDIImpl() }
}

fun loadFeatureModules() {
    loadKoinModules(modules)
}