package br.com.pokedex.pokemons.remote

import br.com.pokedex.pokemons.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.dto.PokemonsListDTO
import kotlinx.coroutines.flow.Flow

interface PokemonsRemoteDataSource {

    suspend fun getPokemons(page: Int, itemsPerPage: Int): Flow<PokemonsListDTO>
    suspend fun getPokemonDetails(name: String): Flow<PokemonDetailsDTO>
    suspend fun getPokemonDetails(id: Int): Flow<PokemonDetailsDTO>

}