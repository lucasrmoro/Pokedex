package br.com.pokedex.pokemons.model

import androidx.annotation.ColorRes
import br.com.pokedex.domain.adapter.AdapterItem
import br.com.pokedex.domain.adapter.AdapterViewType

data class PokemonAbilityItem(
    val ability: PokemonAbility,
    @ColorRes val nameColor: Int,
) : AdapterItem {

    override val itemViewType: AdapterViewType = AdapterViewType.POKEMON_ABILITY

    override fun areItemsTheSame(toCompare: Any): Boolean =
        this.ability.name == (toCompare as? PokemonAbilityItem)?.ability?.name

}