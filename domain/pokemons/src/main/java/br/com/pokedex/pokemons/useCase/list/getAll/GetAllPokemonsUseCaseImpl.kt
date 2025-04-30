package br.com.pokedex.pokemons.useCase.list.getAll

import br.com.pokedex.pokemons.model.PokemonListItems
import br.com.pokedex.pokemons.repository.PokemonsRepository
import kotlinx.coroutines.flow.Flow

internal class GetAllPokemonsUseCaseImpl(
    private val pokemonsRepository: PokemonsRepository
) : GetAllPokemonsUseCase {

    override suspend fun invoke(page: Int, itemsPerPage: Int): Flow<PokemonListItems> =
        pokemonsRepository.getPokemons(page, itemsPerPage)

}