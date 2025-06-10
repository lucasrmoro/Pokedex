package br.com.pokedex.pokemons.useCase.list.get

import br.com.pokedex.pokemons.model.PokemonListItems
import br.com.pokedex.pokemons.repository.PokemonsRepository
import kotlinx.coroutines.flow.Flow

internal class GetPokemonsByNameUseCaseImpl(
    private val pokemonsRepository: PokemonsRepository
) : GetPokemonsByNameUseCase {

    override suspend fun invoke(name: String): Flow<PokemonListItems> =
        pokemonsRepository.getPokemonBy(name)

}