package br.com.pokedex.core_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.pokedex.core_ui.adapter.callbacks.FooAdapterCallbacks
import br.com.pokedex.core_ui.adapter.callbacks.PokemonsListAdapterCallbacks
import br.com.pokedex.core_ui.adapter.callbacks.generic.PokedexGenericAdapterCallback
import br.com.pokedex.core_ui.adapter.diffUtil.GenericItemDiffCallback
import br.com.pokedex.core_ui.adapter.model.Foo
import br.com.pokedex.core_ui.adapter.model.PokemonItem
import br.com.pokedex.core_ui.adapter.model.base.AdapterItem
import br.com.pokedex.core_ui.adapter.model.base.AdapterViewType
import br.com.pokedex.core_ui.adapter.viewHolder.FooViewHolder
import br.com.pokedex.core_ui.adapter.viewHolder.LoadingViewHolder
import br.com.pokedex.core_ui.adapter.viewHolder.PokemonViewHolder
import br.com.pokedex.core_ui.databinding.RvFooItemBinding
import br.com.pokedex.core_ui.databinding.RvLoadingItemBinding
import br.com.pokedex.core_ui.databinding.RvPokemonItemBinding

class PokedexGenericAdapter(
    private val adapterCallbacks: PokedexGenericAdapterCallback? = null
) : ListAdapter<AdapterItem, RecyclerView.ViewHolder>(GenericItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (AdapterViewType.fromValue(viewType)) {
            AdapterViewType.LOADING ->
                LoadingViewHolder(RvLoadingItemBinding.inflate(inflater, parent, false))

            AdapterViewType.FOO ->
                FooViewHolder(
                    RvFooItemBinding.inflate(inflater, parent, false),
                    adapterCallbacks as? FooAdapterCallbacks
                )

            AdapterViewType.POKEMON -> PokemonViewHolder(
                RvPokemonItemBinding.inflate(inflater, parent, false),
                adapterCallbacks as? PokemonsListAdapterCallbacks
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with(holder) {
        val item = getItem(position)

        when (this) {
            is FooViewHolder -> onBind(item as Foo)
            is PokemonViewHolder -> onBind(item as PokemonItem)
        }
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int = currentList[position].itemViewType.ordinal

}