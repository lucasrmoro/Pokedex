package br.com.pokedex.pokemons.list.domain.useCase.get

import br.com.pokedex.pokemons.list.domain.model.PokemonsList
import kotlinx.coroutines.flow.Flow

interface GetPokemonsByNameUseCase {

    suspend operator fun invoke(name: String): Flow<PokemonsList>

}