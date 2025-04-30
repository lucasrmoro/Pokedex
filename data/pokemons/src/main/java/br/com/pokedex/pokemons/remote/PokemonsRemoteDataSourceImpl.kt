package br.com.pokedex.pokemons.remote

import br.com.pokedex.core.base.dataSource.BaseDataSource
import br.com.pokedex.core.ext.ONE
import br.com.pokedex.pokemons.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.dto.PokemonsListDTO
import kotlinx.coroutines.flow.Flow

class PokemonsRemoteDataSourceImpl(
    private val pokemonsService: PokemonsService
) : PokemonsRemoteDataSource, BaseDataSource() {

    override suspend fun getPokemons(page: Int, itemsPerPage: Int): Flow<PokemonsListDTO> = call {
        pokemonsService.getPokemons(
            offset = page.minus(Int.ONE).times(itemsPerPage),
            limit = itemsPerPage
        )
    }

    override suspend fun getPokemonDetails(name: String): Flow<PokemonDetailsDTO> =
        call { pokemonsService.getPokemonDetails(name) }

    override suspend fun getPokemonDetails(id: Int): Flow<PokemonDetailsDTO> =
        call { pokemonsService.getPokemonDetails(id) }

}