package br.com.pokedex.pokemons.list.presentation.activity

import android.os.Bundle
import br.com.pokedex.core_ui.base.activity.BaseNavDrawerContainerActivity
import br.com.pokedex.pokemons.list.di.PokemonsListModule
import br.com.pokedex.pokemons.list.presentation.fragment.PokemonsListFragment
import br.com.pokedex.pokemons.list.presentation.viewModel.PokemonsListViewModel

internal class PokemonsListActivity : BaseNavDrawerContainerActivity<PokemonsListViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PokemonsListModule.init()
        navigate(PokemonsListFragment.newInstance())
    }

}