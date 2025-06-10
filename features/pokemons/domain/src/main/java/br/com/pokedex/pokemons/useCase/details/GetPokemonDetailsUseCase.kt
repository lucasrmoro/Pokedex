package br.com.pokedex.pokemons.useCase.details

import br.com.pokedex.pokemons.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface GetPokemonDetailsUseCase {

    suspend operator fun invoke(id: Int): Flow<PokemonDetails?>

}