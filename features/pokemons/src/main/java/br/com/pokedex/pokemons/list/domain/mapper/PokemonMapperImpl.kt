package br.com.pokedex.pokemons.list.domain.mapper

import br.com.pokedex.core.ext.BAR
import br.com.pokedex.core.ext.SLASH
import br.com.pokedex.core.ext.orZero
import br.com.pokedex.core_ui.adapter.model.PokemonItem
import br.com.pokedex.pokemons.list.data.dto.PokemonDTO
import br.com.pokedex.pokemons.list.data.dto.PokemonDetailsDTO

internal class PokemonMapperImpl : PokemonMapper() {

    override fun toDomainModel(dto: PokemonDetailsDTO) = with(dto) {
        PokemonItem(
            id = id.orZero(),
            name = name.orEmpty().replaceFirstChar { it.uppercase() },
            imageUrl = getImageUrl(id)
        )
    }

    override fun toDomainModel(dto: PokemonDTO): PokemonItem = with(dto) {
        PokemonItem(
            id = url.getPokemonIndex(),
            name = name.orEmpty().replaceFirstChar { it.uppercase() },
            imageUrl = getImageUrl(url.getPokemonIndex())
        )
    }

    override fun toDTO(domainModel: PokemonItem): PokemonDTO = with(domainModel) {
        PokemonDTO(name = name, url = null)
    }

    private fun String?.getPokemonIndex() =
        orEmpty().trim(Char.SLASH).substringAfterLast(String.BAR).toInt()

    private fun getImageUrl(pokemonIndex: Int?) = pokemonIndex?.let {
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$it.png"
    }

}