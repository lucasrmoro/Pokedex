package br.com.pokedex.pokemons.details.di

import br.com.pokedex.pokemons.details.viewModel.PokemonDetailsViewModel
import br.com.pokedex.pokemons.details.viewModel.PokemonEvolutionTabViewModel
import br.com.pokedex.pokemons.details.viewModel.PokemonMovesTabViewModel
import br.com.pokedex.pokemons.details.viewModel.PokemonStatsTabViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val pokemonsDetailsModule = module {
    viewModel { PokemonDetailsViewModel(getPokemonDetailsUseCase = get()) }
    viewModel { PokemonStatsTabViewModel(getPokemonAbilityUseCase = get()) }
    viewModel { PokemonEvolutionTabViewModel() }
    viewModel { PokemonMovesTabViewModel() }
}