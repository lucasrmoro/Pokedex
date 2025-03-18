package br.com.pokedex.pokemons.list.presentation.fragment

import br.com.pokedex.core_ui.adapter.PokedexGenericAdapter
import br.com.pokedex.core_ui.adapter.model.PokemonItem
import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.core_ui.ext.showToast
import br.com.pokedex.pokemons.list.presentation.viewModel.PokemonsListViewModel
import br.com.pokedex.pokemonsList.databinding.FragPokemonsListBinding

class PokemonsListFragment private constructor() :
    BaseFragment<FragPokemonsListBinding, PokemonsListViewModel>(FragPokemonsListBinding::inflate) {

    private val pokemonsAdapter = PokedexGenericAdapter()

    override fun setupViews() {
        setupListeners()
        setupObservers()
    }

    override fun onFragmentVisible() {
        showScreenLoading()
        binding.recyclerView.resetCurrentPage()
        viewModel.loadItems()
    }

    override fun setupListeners() = with(binding) {
        recyclerView.setup(adapter = pokemonsAdapter, callbacks = viewModel)
    }

    override fun setupObservers() = with(viewModel) {
        onFetchPokemons.observe(viewLifecycleOwner, ::onLoadItems)
    }

    private fun onLoadItems(result: Pair<List<PokemonItem>?, String?>) = with(binding) {
        dismissScreenLoading()
        result.first?.let {
            recyclerView.onLoadItemsSuccess(it)
        } ?: run {
            recyclerView.onLoadItemsError()
            showToast(result.second ?: defaultErrorMessage)
        }
    }

    companion object {
        fun newInstance() = PokemonsListFragment()
    }
}