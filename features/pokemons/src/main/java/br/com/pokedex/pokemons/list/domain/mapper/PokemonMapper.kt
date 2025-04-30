package br.com.pokedex.pokemons.list.domain.mapper

import br.com.pokedex.pokemons.list.data.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.list.data.dto.PokemonsListDTO
import br.com.pokedex.pokemons.list.domain.model.PokemonDetails
import br.com.pokedex.pokemons.list.domain.model.PokemonsList
import kotlinx.coroutines.flow.Flow

interface PokemonMapper {

    fun toPokemonsList(dto: Flow<PokemonsListDTO>): Flow<PokemonsList>
    fun toPokemonsList(dto: Flow<PokemonDetailsDTO>, pagesCount: Int): Flow<PokemonsList>
    fun toPokemonDetails(dto: Flow<PokemonDetailsDTO>): Flow<PokemonDetails>

}