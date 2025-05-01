package br.com.pokedex.core_ui.adapter.callbacks

import br.com.pokedex.core_ui.adapter.callbacks.generic.PokedexGenericAdapterCallback
import br.com.pokedex.domain.adapter.model.pokemons.PokemonItem

interface PokemonsListAdapterCallbacks : PokedexGenericAdapterCallback {

    fun onClick(pokemon: PokemonItem)
    fun onFavorite(pokemon: PokemonItem, isFavorite: Boolean)

}