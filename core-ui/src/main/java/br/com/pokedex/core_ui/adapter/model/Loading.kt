package br.com.pokedex.core_ui.adapter.model

import br.com.pokedex.core_ui.adapter.model.base.AdapterItem
import br.com.pokedex.core_ui.adapter.model.base.AdapterViewType

data class Loading(
    val id: String = "LoadingId"
) : AdapterItem {

    override val itemViewType = AdapterViewType.LOADING

    override fun areItemsTheSame(toCompare: Any): Boolean =
        this.id == (toCompare as? Loading)?.id
}