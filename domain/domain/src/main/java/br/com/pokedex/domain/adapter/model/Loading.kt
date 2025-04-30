package br.com.pokedex.domain.adapter.model

import br.com.pokedex.domain.adapter.AdapterItem
import br.com.pokedex.domain.adapter.AdapterViewType

data class Loading(
    val id: String = "LoadingId"
) : AdapterItem {

    override val itemViewType = AdapterViewType.LOADING

    override fun areItemsTheSame(toCompare: Any): Boolean =
        this.id == (toCompare as? Loading)?.id
}