package br.com.pokedex.pokemons.useCase.details

import br.com.pokedex.pokemons.model.PokemonDetails
import br.com.pokedex.pokemons.repository.PokemonsRepository
import kotlinx.coroutines.flow.Flow

internal class GetPokemonDetailsUseCaseImpl(
    private val pokemonsRepository: PokemonsRepository
) : GetPokemonDetailsUseCase {

    override suspend fun invoke(id: Int): Flow<PokemonDetails?> =
        pokemonsRepository.getPokemonDetails(id)

}