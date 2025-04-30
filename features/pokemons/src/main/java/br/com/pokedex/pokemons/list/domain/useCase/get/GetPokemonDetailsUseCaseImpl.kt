package br.com.pokedex.pokemons.list.domain.useCase.get

import br.com.pokedex.pokemons.list.domain.model.PokemonDetails
import br.com.pokedex.pokemons.list.domain.repository.PokemonsRepository
import kotlinx.coroutines.flow.Flow

internal class GetPokemonDetailsUseCaseImpl(
    private val pokemonsRepository: PokemonsRepository
) : GetPokemonDetailsUseCase {

    override suspend fun invoke(id: Int): Flow<PokemonDetails> =
        pokemonsRepository.getPokemonDetails(id)

}