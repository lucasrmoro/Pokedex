package br.com.pokedex.core_ui.adapter.diffUtil

import androidx.recyclerview.widget.DiffUtil
import br.com.pokedex.domain.adapter.AdapterItem

class GenericItemDiffCallback : DiffUtil.ItemCallback<AdapterItem>() {

    override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
        oldItem.areContentsTheSame(newItem)

}