package br.com.pokedex.pokemons.model

import br.com.pokedex.domain.adapter.AdapterItem
import br.com.pokedex.domain.adapter.AdapterViewType

data class PokemonTypeItem(
    val type: PokemonType
) : AdapterItem {

    override val itemViewType: AdapterViewType = AdapterViewType.POKEMON_TYPE

    override fun areItemsTheSame(toCompare: Any): Boolean =
        this.type == (toCompare as? PokemonTypeItem)?.type

}