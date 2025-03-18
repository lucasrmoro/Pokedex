package br.com.pokedex.pokemons.list.domain.useCase.getAll

import br.com.pokedex.pokemons.list.domain.model.PokemonsList
import kotlinx.coroutines.flow.Flow

interface GetAllPokemonsUseCase {

    suspend operator fun invoke(page: Int, itemsPerPage: Int): Flow<PokemonsList>

}