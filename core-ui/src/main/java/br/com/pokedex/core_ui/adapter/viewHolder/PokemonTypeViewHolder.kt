package br.com.pokedex.core_ui.adapter.viewHolder

import android.graphics.drawable.GradientDrawable
import br.com.pokedex.core_ui.adapter.base.BaseViewHolder
import br.com.pokedex.core_ui.databinding.RvPokemonTypeItemBinding
import br.com.pokedex.pokemons.model.PokemonTypeItem

class PokemonTypeViewHolder(binding: RvPokemonTypeItemBinding) :
    BaseViewHolder<PokemonTypeItem, RvPokemonTypeItemBinding>(binding) {

    override fun onBind(item: PokemonTypeItem) = bind {
        ivType.setImageResource(item.type.icon)
        tvType.text = getString(item.type.text)
        llContent.background = item.getBackgroundColor()
    }

    private fun PokemonTypeItem.getBackgroundColor() = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(getColor(type.startGradientColor), getColor(type.endGradientColor))
    )

}