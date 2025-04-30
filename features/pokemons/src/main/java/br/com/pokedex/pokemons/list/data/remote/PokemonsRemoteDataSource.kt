package br.com.pokedex.pokemons.list.data.remote

import br.com.pokedex.pokemons.list.data.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.list.data.dto.PokemonsListDTO
import kotlinx.coroutines.flow.Flow

interface PokemonsRemoteDataSource {

    suspend fun getPokemons(page: Int, itemsPerPage: Int): Flow<PokemonsListDTO>
    suspend fun getPokemonDetails(name: String): Flow<PokemonDetailsDTO>
    suspend fun getPokemonDetails(id: Int): Flow<PokemonDetailsDTO>

}