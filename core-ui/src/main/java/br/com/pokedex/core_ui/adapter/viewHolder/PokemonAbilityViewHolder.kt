package br.com.pokedex.core_ui.adapter.viewHolder

import androidx.core.view.isGone
import br.com.pokedex.core_ui.adapter.base.BaseViewHolder
import br.com.pokedex.core_ui.databinding.RvPokemonAbilityItemBinding
import br.com.pokedex.core_ui.ext.isShimmerVisible
import br.com.pokedex.pokemons.model.PokemonAbilityItem

class PokemonAbilityViewHolder(binding: RvPokemonAbilityItemBinding) :
    BaseViewHolder<PokemonAbilityItem, RvPokemonAbilityItemBinding>(binding) {

    fun onBind(item: PokemonAbilityItem, isLastItem: Boolean) = bind {
        tvName.setTextColor(getColor(item.nameColor))
        tvName.text = item.ability.name
        tvDescription.text = item.ability.description
        skeletonDescription.isShimmerVisible = item.ability.description.isEmpty()
        vDivider.isGone = isLastItem
    }

}