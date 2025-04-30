package br.com.pokedex.pokemons.list.domain.mapper

import br.com.pokedex.core.ext.BAR
import br.com.pokedex.core.ext.SLASH
import br.com.pokedex.core.ext.orZero
import br.com.pokedex.core.ext.update
import br.com.pokedex.core_ui.adapter.model.PokemonItem
import br.com.pokedex.pokemons.list.data.dto.PokemonDTO
import br.com.pokedex.pokemons.list.data.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.list.data.dto.PokemonsListDTO
import br.com.pokedex.pokemons.list.domain.model.PokemonDetails
import br.com.pokedex.pokemons.list.domain.model.PokemonsList
import kotlinx.coroutines.flow.Flow

internal class PokemonMapperImpl : PokemonMapper {

    override fun toPokemonsList(dto: Flow<PokemonsListDTO>): Flow<PokemonsList> =
        dto.update {
            PokemonsList(
                pagesCount = count.orZero(),
                pokemons = pokemons?.map { it.toPokemonItem() }.orEmpty()
            )
        }

    override fun toPokemonsList(dto: Flow<PokemonDetailsDTO>, pagesCount: Int): Flow<PokemonsList> =
        dto.update {
            PokemonsList(
                pagesCount = pagesCount,
                pokemons = listOf(toPokemonItem())
            )
        }

    override fun toPokemonDetails(dto: Flow<PokemonDetailsDTO>): Flow<PokemonDetails> = dto.update {
        PokemonDetails(
            id = id.orZero()
        )
    }

    private fun PokemonDTO.toPokemonItem() = PokemonItem(
        id = url.getPokemonIndex(),
        name = name.orEmpty().replaceFirstChar { char -> char.uppercase() },
        imageUrl = getImageUrl(url.getPokemonIndex())
    )

    private fun PokemonDetailsDTO.toPokemonItem() = PokemonItem(
        id = id.orZero(),
        name = name.orEmpty().replaceFirstChar { it.uppercase() },
        imageUrl = getImageUrl(id)
    )

    private fun String?.getPokemonIndex() =
        orEmpty().trim(Char.SLASH).substringAfterLast(String.BAR).toInt()

    private fun getImageUrl(pokemonIndex: Int?) = pokemonIndex?.let {
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$it.png"
    }

}