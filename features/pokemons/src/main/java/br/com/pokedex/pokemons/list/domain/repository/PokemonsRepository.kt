package br.com.pokedex.pokemons.list.domain.repository

import br.com.pokedex.pokemons.list.domain.model.PokemonDetails
import br.com.pokedex.pokemons.list.domain.model.PokemonsList
import kotlinx.coroutines.flow.Flow

interface PokemonsRepository {

    suspend fun getPokemons(page: Int, itemsPerPage: Int): Flow<PokemonsList>
    suspend fun getPokemonBy(name: String): Flow<PokemonsList>
    suspend fun getPokemonDetails(id: Int): Flow<PokemonDetails>

}