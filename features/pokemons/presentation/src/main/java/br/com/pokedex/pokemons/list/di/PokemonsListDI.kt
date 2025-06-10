package br.com.pokedex.pokemons.list.di

import br.com.pokedex.pokemons.list.viewModel.PokemonsListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val pokemonsListModule = module {
    viewModel {
        PokemonsListViewModel(
            resourcesProvider = get(),
            getAllPokemonsUseCase = get(),
            getPokemonsByNameUseCase = get()
        )
    }
}