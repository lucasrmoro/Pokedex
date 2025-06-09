package br.com.pokedex.pokemons.list.activity

import android.os.Bundle
import br.com.pokedex.core_ui.base.activity.BaseNavDrawerContainerActivity
import br.com.pokedex.pokemons.list.di.PokemonsListModule
import br.com.pokedex.pokemons.list.fragment.PokemonsListFragment
import br.com.pokedex.pokemons.list.viewModel.PokemonsListViewModel

internal class PokemonsListActivity : BaseNavDrawerContainerActivity<PokemonsListViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PokemonsListModule.init()
        navigate(PokemonsListFragment.newInstance())
    }

}