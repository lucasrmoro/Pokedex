package br.com.pokedex.pokemons.useCase.list.get

import br.com.pokedex.pokemons.model.PokemonListItems
import kotlinx.coroutines.flow.Flow

interface GetPokemonsByNameUseCase {

    suspend operator fun invoke(name: String): Flow<PokemonListItems>

}