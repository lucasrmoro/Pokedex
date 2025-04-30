package br.com.pokedex.pokemons.list.domain.useCase.get

import br.com.pokedex.pokemons.list.domain.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface GetPokemonDetailsUseCase {

    suspend operator fun invoke(id: Int): Flow<PokemonDetails>

}