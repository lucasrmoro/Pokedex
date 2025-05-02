package br.com.pokedex.pokemons.details.di

import br.com.pokedex.pokemons.details.viewModel.PokemonDetailsViewModel
import br.com.pokedex.pokemons.details.viewModel.PokemonEvolutionTabViewModel
import br.com.pokedex.pokemons.details.viewModel.PokemonMovesTabViewModel
import br.com.pokedex.pokemons.details.viewModel.PokemonStatsTabViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val module = module {
    viewModel { PokemonDetailsViewModel(getPokemonDetailsUseCase = get()) }
    viewModel { PokemonStatsTabViewModel() }
    viewModel { PokemonEvolutionTabViewModel() }
    viewModel { PokemonMovesTabViewModel() }
}

object PokemonDetailsModule {
    fun init() {
        loadKoinModules(module)
    }
}