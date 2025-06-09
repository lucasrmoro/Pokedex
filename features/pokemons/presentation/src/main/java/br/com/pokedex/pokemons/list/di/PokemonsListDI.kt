package br.com.pokedex.pokemons.list.di

import br.com.pokedex.pokemons.list.viewModel.PokemonsListViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val module = module {
    viewModel {
        PokemonsListViewModel(
            resourcesProvider = get(),
            getAllPokemonsUseCase = get(),
            getPokemonsByNameUseCase = get()
        )
    }
}

object PokemonsListModule {
    fun init() {
        loadKoinModules(module)
    }
}