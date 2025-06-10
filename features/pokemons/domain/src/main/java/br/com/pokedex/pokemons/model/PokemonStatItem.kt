package br.com.pokedex.pokemons.model

import androidx.annotation.ColorRes
import br.com.pokedex.domain.adapter.AdapterItem
import br.com.pokedex.domain.adapter.AdapterViewType

data class PokemonStatItem(
    val name: String,
    val value: Int,
    @ColorRes val startGradientColor: Int,
    @ColorRes val endGradientColor: Int
) : AdapterItem {

    override val itemViewType: AdapterViewType = AdapterViewType.POKEMON_STAT

    override fun areItemsTheSame(toCompare: Any): Boolean =
        this.name == (toCompare as? PokemonStatItem)?.name

}