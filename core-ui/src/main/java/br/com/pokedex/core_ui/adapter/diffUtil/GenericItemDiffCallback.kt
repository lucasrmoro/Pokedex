package br.com.pokedex.core_ui.adapter.diffUtil

import androidx.recyclerview.widget.DiffUtil
import br.com.pokedex.domain.adapter.AdapterItem

class GenericItemDiffCallback : DiffUtil.ItemCallback<br.com.pokedex.domain.adapter.AdapterItem>() {

    override fun areItemsTheSame(oldItem: br.com.pokedex.domain.adapter.AdapterItem, newItem: br.com.pokedex.domain.adapter.AdapterItem): Boolean =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: br.com.pokedex.domain.adapter.AdapterItem, newItem: br.com.pokedex.domain.adapter.AdapterItem): Boolean =
        oldItem.areContentsTheSame(newItem)

}