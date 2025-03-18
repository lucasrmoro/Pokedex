package br.com.pokedex.pokemons.list.domain.mapper

import br.com.pokedex.core.ext.BAR
import br.com.pokedex.core_ui.adapter.model.PokemonItem
import br.com.pokedex.pokemons.list.data.dto.PokemonDTO

internal class PokemonMapperImpl : PokemonMapper() {

    override fun toDomainModel(dto: PokemonDTO): PokemonItem = with(dto) {
        PokemonItem(
            id = getPokemonIndex(),
            name = name.orEmpty().replaceFirstChar { it.uppercase() },
            imageUrl = getImageUrl(getPokemonIndex())
        )
    }

    override fun toDTO(domainModel: PokemonItem): PokemonDTO = with(domainModel) {
        PokemonDTO(name = name, url = null)
    }

    private fun PokemonDTO.getPokemonIndex() =
        url.orEmpty().trim('/').substringAfterLast(String.BAR).toInt()

    private fun getImageUrl(pokemonIndex: Int?) = pokemonIndex?.let {
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$it.png"
    }

}