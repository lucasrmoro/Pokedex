package br.com.pokedex.pokemons.repository

import br.com.pokedex.pokemons.model.PokemonDetails
import br.com.pokedex.pokemons.model.PokemonListItems
import kotlinx.coroutines.flow.Flow

interface PokemonsRepository {

    suspend fun getPokemons(page: Int, itemsPerPage: Int): Flow<PokemonListItems>
    suspend fun getPokemonBy(name: String): Flow<PokemonListItems>
    suspend fun getPokemonDetails(id: Int): Flow<PokemonDetails?>

}