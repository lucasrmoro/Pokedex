package br.com.pokedex.pokemons.repository

import br.com.pokedex.core.ext.ONE
import br.com.pokedex.core.ext.collectResultSafely
import br.com.pokedex.core.ext.execute
import br.com.pokedex.core.ext.update
import br.com.pokedex.pokemons.mapper.PokemonMapper
import br.com.pokedex.pokemons.model.PokemonDetails
import br.com.pokedex.pokemons.model.PokemonListItems
import br.com.pokedex.pokemons.remote.PokemonsRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow

class PokemonsRepositoryImpl(
    private val pokemonsRemoteDataSource: PokemonsRemoteDataSource,
    private val pokemonMapper: PokemonMapper,
    private val dispatcher: CoroutineDispatcher
) : PokemonsRepository {

    override suspend fun getPokemons(page: Int, itemsPerPage: Int): Flow<PokemonListItems> =
        dispatcher.execute {
            pokemonMapper.toPokemonListItems(
                pokemonsRemoteDataSource.getPokemons(
                    page,
                    itemsPerPage
                )
            )
        }

    override suspend fun getPokemonBy(name: String): Flow<PokemonListItems> =
        dispatcher.execute {
            pokemonMapper.toPokemonListItems(
                dto = pokemonsRemoteDataSource.getPokemonDetails(name),
                pagesCount = Int.ONE
            )
        }

    override suspend fun getPokemonDetails(id: Int): Flow<PokemonDetails?> =
        dispatcher.execute {
            getPokemonsDetails(id).update {
                val types =
                    types?.mapNotNull { dto -> dto.type?.name?.let { getPokemonTypeDetails(it) } }
                        .orEmpty().awaitAll()
                pokemonMapper.toPokemonDetails(
                    dto = this,
                    damageRelations = types.mapNotNull { it?.damageRelations }
                )
            }
        }

    private suspend fun getPokemonsDetails(id: Int) = pokemonsRemoteDataSource.getPokemonDetails(id)

    private suspend fun getPokemonTypeDetails(typeName: String) = dispatcher.execute {
        async {
            pokemonsRemoteDataSource.getPokemonTypeDetails(typeName).collectResultSafely()
        }
    }

}