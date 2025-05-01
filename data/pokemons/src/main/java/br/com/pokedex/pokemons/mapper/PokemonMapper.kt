package br.com.pokedex.pokemons.mapper

import br.com.pokedex.pokemons.model.PokemonListItems
import br.com.pokedex.pokemons.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.dto.PokemonsListDTO
import br.com.pokedex.pokemons.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface PokemonMapper {

    fun toPokemonListItems(dto: Flow<PokemonsListDTO>): Flow<PokemonListItems>
    fun toPokemonListItems(dto: Flow<PokemonDetailsDTO>, pagesCount: Int): Flow<PokemonListItems>
    fun toPokemonDetails(dto: Flow<PokemonDetailsDTO>): Flow<PokemonDetails>

}