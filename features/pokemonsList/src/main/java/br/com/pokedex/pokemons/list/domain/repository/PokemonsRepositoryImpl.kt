package br.com.pokedex.pokemons.list.domain.repository

import br.com.pokedex.core.ext.orZero
import br.com.pokedex.core.ext.update
import br.com.pokedex.domain.ext.execute
import br.com.pokedex.pokemons.list.data.remote.PokemonsRemoteDataSource
import br.com.pokedex.pokemons.list.domain.mapper.PokemonMapper
import br.com.pokedex.pokemons.list.domain.model.PokemonsList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

internal class PokemonsRepositoryImpl(
    private val pokemonsRemoteDataSource: PokemonsRemoteDataSource,
    private val pokemonMapper: PokemonMapper,
    private val dispatcher: CoroutineDispatcher
) : PokemonsRepository {

    override suspend fun getPokemons(page: Int, itemsPerPage: Int): Flow<PokemonsList> =
        dispatcher.execute {
            pokemonsRemoteDataSource.getPokemons(page, itemsPerPage).update {
                PokemonsList(
                    pagesCount = count.orZero(),
                    pokemons = pokemonMapper.toDomainModelList(pokemons.orEmpty())
                )
            }
        }

}