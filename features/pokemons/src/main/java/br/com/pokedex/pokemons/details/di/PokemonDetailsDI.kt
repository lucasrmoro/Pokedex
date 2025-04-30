package br.com.pokedex.pokemons.details.di

import br.com.pokedex.pokemons.details.presentation.viewModel.PokemonDetailsViewModel
import br.com.pokedex.pokemons.details.presentation.viewModel.PokemonEvolutionTabViewModel
import br.com.pokedex.pokemons.details.presentation.viewModel.PokemonMovesTabViewModel
import br.com.pokedex.pokemons.details.presentation.viewModel.PokemonStatsTabViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val dataModule = module {}

private val domainModule = module {}

private val viewModelModule = module {
    viewModel { PokemonDetailsViewModel() }
    viewModel { PokemonStatsTabViewModel() }
    viewModel { PokemonEvolutionTabViewModel() }
    viewModel { PokemonMovesTabViewModel() }
}

internal object PokemonDetailsModule {
    fun init() {
        loadKoinModules(listOf(dataModule, domainModule, viewModelModule))
    }
}