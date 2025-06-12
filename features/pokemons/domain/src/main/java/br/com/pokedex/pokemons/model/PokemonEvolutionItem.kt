package br.com.pokedex.pokemons.model

import androidx.annotation.ColorRes
import br.com.pokedex.domain.adapter.AdapterItem
import br.com.pokedex.domain.adapter.AdapterViewType

class PokemonEvolutionItem(
    val from: String,
    val fromImgUrl: String,
    val to: String,
    val toImgUrl: String,
    val evolutionNecessity: String,
    @ColorRes val mainPokemonColor: Int
) : AdapterItem {

    override val itemViewType: AdapterViewType = AdapterViewType.POKEMON_EVOLUTION

    override fun areItemsTheSame(toCompare: Any): Boolean =
        this.from == (toCompare as? PokemonEvolutionItem)?.from

}