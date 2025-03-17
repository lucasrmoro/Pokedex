package br.com.pokedex.core_ui.adapter.callbacks

import br.com.pokedex.core_ui.adapter.callbacks.generic.PokedexGenericAdapterCallback
import br.com.pokedex.core_ui.adapter.model.Foo

interface FooAdapterCallbacks : PokedexGenericAdapterCallback {

    fun onFooClicked(foo: Foo)

}