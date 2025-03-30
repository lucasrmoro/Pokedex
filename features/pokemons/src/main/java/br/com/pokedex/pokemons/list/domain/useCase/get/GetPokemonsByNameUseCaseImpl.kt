package br.com.pokedex.pokemons.list.domain.useCase.get

import br.com.pokedex.pokemons.list.domain.model.PokemonsList
import br.com.pokedex.pokemons.list.domain.repository.PokemonsRepository
import kotlinx.coroutines.flow.Flow

internal class GetPokemonsByNameUseCaseImpl(
    private val pokemonsRepository: PokemonsRepository
) : GetPokemonsByNameUseCase {

    override suspend fun invoke(name: String): Flow<PokemonsList> =
        pokemonsRepository.getPokemonBy(name)

}