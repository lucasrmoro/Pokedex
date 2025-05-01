package br.com.pokedex.pokemons.mapper

import br.com.pokedex.core.ext.BAR
import br.com.pokedex.core.ext.SLASH
import br.com.pokedex.core.ext.orZero
import br.com.pokedex.core.ext.update
import br.com.pokedex.domain.adapter.model.pokemons.PokemonItem
import br.com.pokedex.pokemons.model.PokemonListItems
import br.com.pokedex.pokemons.dto.PokemonDTO
import br.com.pokedex.pokemons.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.dto.PokemonsListDTO
import br.com.pokedex.pokemons.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

class PokemonMapperImpl : PokemonMapper {

    override fun toPokemonListItems(dto: Flow<PokemonsListDTO>): Flow<PokemonListItems> =
        dto.update {
            PokemonListItems(
                pagesCount = count.orZero(),
                pokemons = pokemons?.map { it.toPokemonItem() }.orEmpty()
            )
        }

    override fun toPokemonListItems(
        dto: Flow<PokemonDetailsDTO>,
        pagesCount: Int
    ): Flow<PokemonListItems> = dto.update {
        PokemonListItems(pagesCount = pagesCount, pokemons = listOf(toPokemonItem()))
    }

    override fun toPokemonDetails(dto: Flow<PokemonDetailsDTO>): Flow<PokemonDetails> =
        dto.update { PokemonDetails(id = id.orZero()) }

    private fun PokemonDTO.toPokemonItem() = PokemonItem(
        id = url.getPokemonIndex(),
        name = name.orEmpty().replaceFirstChar { char -> char.uppercase() },
        imageUrl = getImageUrl(url.getPokemonIndex())
    )

    private fun PokemonDetailsDTO.toPokemonItem() = PokemonItem(
        id = id.orZero(),
        name = name.orEmpty(),
        imageUrl = getImageUrl(id),
        isFavorite = false
    )

    private fun String?.getPokemonIndex() =
        orEmpty().trim(Char.SLASH).substringAfterLast(String.BAR).toInt()

    private fun getImageUrl(pokemonIndex: Int?) = pokemonIndex?.let {
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$it.png"
    }

}