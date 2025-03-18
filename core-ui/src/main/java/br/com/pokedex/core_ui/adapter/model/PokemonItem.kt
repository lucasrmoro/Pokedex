package br.com.pokedex.core_ui.adapter.model

import br.com.pokedex.core.ext.FOUR
import br.com.pokedex.core.ext.HASH
import br.com.pokedex.core.ext.ZERO
import br.com.pokedex.core_ui.adapter.model.base.AdapterItem
import br.com.pokedex.core_ui.adapter.model.base.AdapterViewType

data class PokemonItem(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val isFavorite: Boolean = false
) : AdapterItem {

    override val itemViewType: AdapterViewType = AdapterViewType.POKEMON

    override fun areItemsTheSame(toCompare: Any): Boolean =
        this.name == (toCompare as? PokemonItem)?.name

    val index: String
        get() = String.HASH.plus(id.toString().padStart(length = Int.FOUR, padChar = Char.ZERO))

}