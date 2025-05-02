package br.com.pokedex.core_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.pokedex.core_ui.adapter.callbacks.PokemonsListAdapterCallbacks
import br.com.pokedex.core_ui.adapter.callbacks.generic.PokedexGenericAdapterCallback
import br.com.pokedex.core_ui.adapter.diffUtil.GenericItemDiffCallback
import br.com.pokedex.core_ui.adapter.viewHolder.LoadingViewHolder
import br.com.pokedex.core_ui.adapter.viewHolder.PokemonStatViewHolder
import br.com.pokedex.core_ui.adapter.viewHolder.PokemonTypeViewHolder
import br.com.pokedex.core_ui.adapter.viewHolder.PokemonViewHolder
import br.com.pokedex.core_ui.databinding.RvLoadingItemBinding
import br.com.pokedex.core_ui.databinding.RvPokemonItemBinding
import br.com.pokedex.core_ui.databinding.RvPokemonStatItemBinding
import br.com.pokedex.core_ui.databinding.RvPokemonTypeItemBinding
import br.com.pokedex.domain.adapter.AdapterItem
import br.com.pokedex.domain.adapter.AdapterViewType
import br.com.pokedex.pokemons.model.PokemonItem
import br.com.pokedex.pokemons.model.PokemonStatItem
import br.com.pokedex.pokemons.model.PokemonTypeItem

class PokedexGenericAdapter(
    private val adapterCallbacks: PokedexGenericAdapterCallback? = null
) : ListAdapter<AdapterItem, RecyclerView.ViewHolder>(GenericItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (AdapterViewType.fromValue(viewType)) {
            AdapterViewType.LOADING ->
                LoadingViewHolder(RvLoadingItemBinding.inflate(inflater, parent, false))

            AdapterViewType.POKEMON -> PokemonViewHolder(
                RvPokemonItemBinding.inflate(inflater, parent, false),
                adapterCallbacks as? PokemonsListAdapterCallbacks
            )

            AdapterViewType.POKEMON_TYPE -> PokemonTypeViewHolder(
                RvPokemonTypeItemBinding.inflate(inflater, parent, false)
            )

            AdapterViewType.POKEMON_STAT -> PokemonStatViewHolder(
                RvPokemonStatItemBinding.inflate(inflater, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with(holder) {
        val item = getItem(position)

        when (this) {
            is PokemonViewHolder -> onBind(item as PokemonItem)
            is PokemonTypeViewHolder -> onBind(item as PokemonTypeItem)
            is PokemonStatViewHolder -> onBind(item as PokemonStatItem)
        }
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int = currentList[position].itemViewType.ordinal

}