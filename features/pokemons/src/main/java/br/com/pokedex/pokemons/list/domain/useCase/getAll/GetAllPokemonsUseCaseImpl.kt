package br.com.pokedex.pokemons.list.domain.useCase.getAll

import br.com.pokedex.pokemons.list.domain.model.PokemonsList
import br.com.pokedex.pokemons.list.domain.repository.PokemonsRepository
import kotlinx.coroutines.flow.Flow

internal class GetAllPokemonsUseCaseImpl(
    private val pokemonsRepository: PokemonsRepository
) : GetAllPokemonsUseCase {

    override suspend fun invoke(page: Int, itemsPerPage: Int): Flow<PokemonsList> =
        pokemonsRepository.getPokemons(page, itemsPerPage)

}