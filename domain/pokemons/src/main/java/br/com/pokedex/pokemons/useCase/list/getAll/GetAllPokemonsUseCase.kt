package br.com.pokedex.pokemons.useCase.list.getAll

import br.com.pokedex.pokemons.model.PokemonListItems
import kotlinx.coroutines.flow.Flow

interface GetAllPokemonsUseCase {

    suspend operator fun invoke(page: Int, itemsPerPage: Int): Flow<PokemonListItems>

}