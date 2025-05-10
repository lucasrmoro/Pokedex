package br.com.pokedex.pokemons.mapper

import br.com.pokedex.pokemons.dto.DamageRelationsDTO
import br.com.pokedex.pokemons.dto.PokemonAbilitiesDTO
import br.com.pokedex.pokemons.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.dto.PokemonsListDTO
import br.com.pokedex.pokemons.model.PokemonAbility
import br.com.pokedex.pokemons.model.PokemonDetails
import br.com.pokedex.pokemons.model.PokemonListItems
import kotlinx.coroutines.flow.Flow

interface PokemonMapper {

    fun toPokemonListItems(dto: Flow<PokemonsListDTO>): Flow<PokemonListItems>
    fun toPokemonListItems(dto: Flow<PokemonDetailsDTO>, pagesCount: Int): Flow<PokemonListItems>
    suspend fun toPokemonDetails(
        dto: PokemonDetailsDTO,
        damageRelations: List<DamageRelationsDTO>
    ): PokemonDetails?

    fun toPokemonAbility(dto: Flow<PokemonAbilitiesDTO>): Flow<PokemonAbility>

}