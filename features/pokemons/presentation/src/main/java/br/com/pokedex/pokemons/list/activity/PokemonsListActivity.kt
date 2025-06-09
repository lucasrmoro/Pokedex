package br.com.pokedex.pokemons.list.activity

import android.os.Bundle
import br.com.pokedex.core_ui.base.activity.BaseNavDrawerContainerActivity
import br.com.pokedex.pokemons.PokemonsDI
import br.com.pokedex.pokemons.list.fragment.PokemonsListFragment
import br.com.pokedex.pokemons.list.viewModel.PokemonsListViewModel
import org.koin.android.ext.android.inject

class PokemonsListActivity : BaseNavDrawerContainerActivity<PokemonsListViewModel>() {

    private val pokemonsDI by inject<PokemonsDI>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonsDI.load()
        navigate(PokemonsListFragment.newInstance())
    }

}