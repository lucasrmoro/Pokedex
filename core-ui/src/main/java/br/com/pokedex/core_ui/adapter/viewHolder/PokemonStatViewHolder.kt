package br.com.pokedex.core_ui.adapter.viewHolder

import android.graphics.drawable.GradientDrawable
import br.com.pokedex.core.ext.THIRTY
import br.com.pokedex.core.ext.THREE
import br.com.pokedex.core.ext.ZERO
import br.com.pokedex.core_ui.adapter.base.BaseViewHolder
import br.com.pokedex.core_ui.databinding.RvPokemonStatItemBinding
import br.com.pokedex.core_ui.ext.withAlpha
import br.com.pokedex.pokemons.model.PokemonStatItem

class PokemonStatViewHolder(binding: RvPokemonStatItemBinding) :
    BaseViewHolder<PokemonStatItem, RvPokemonStatItemBinding>(binding) {

    override fun onBind(item: PokemonStatItem) = bind {
        tvStat.text = item.name
        tvStat.setTextColor(getColor(item.startGradientColor))
        tvStatValue.text = item.value.toString().padStart(length = Int.THREE, padChar = Char.ZERO)
        linearProgressIndicator.setIndicatorColor(
            getColor(item.startGradientColor),
            getColor(item.endGradientColor)
        )
        GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(getColor(item.startGradientColor), getColor(item.endGradientColor))
        )
        linearProgressIndicator.trackColor = getColor(item.startGradientColor).withAlpha(Int.THIRTY)
        linearProgressIndicator.setProgress(item.value, true)
    }

}