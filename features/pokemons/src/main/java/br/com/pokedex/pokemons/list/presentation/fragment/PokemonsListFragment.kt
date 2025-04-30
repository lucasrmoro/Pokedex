package br.com.pokedex.pokemons.list.presentation.fragment

import androidx.appcompat.widget.SearchView
import br.com.pokedex.core.ext.hideKeyboard
import br.com.pokedex.core_ui.adapter.PokedexGenericAdapter
import br.com.pokedex.core_ui.adapter.callbacks.PokemonsListAdapterCallbacks
import br.com.pokedex.core_ui.adapter.model.PokemonItem
import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.core_ui.ext.hide
import br.com.pokedex.core_ui.ext.openActivity
import br.com.pokedex.core_ui.ext.setHomeAsUpIndicator
import br.com.pokedex.core_ui.ext.setMenu
import br.com.pokedex.core_ui.ext.setOnQueryTextChangeDebouncing
import br.com.pokedex.core_ui.ext.show
import br.com.pokedex.core_ui.ext.showToast
import br.com.pokedex.pokemons.R
import br.com.pokedex.pokemons.databinding.FragPokemonsListBinding
import br.com.pokedex.pokemons.details.presentation.activity.PokemonDetailsActivity
import br.com.pokedex.pokemons.list.presentation.viewModel.PokemonsListViewModel
import timber.log.Timber
import br.com.pokedex.core_ui.R as coreUiR

internal class PokemonsListFragment private constructor() :
    BaseFragment<FragPokemonsListBinding, PokemonsListViewModel>(FragPokemonsListBinding::inflate) {

    private val pokemonsAdapter = PokedexGenericAdapter(
        object : PokemonsListAdapterCallbacks {
            override fun onClick(pokemon: PokemonItem) {
                openActivity<PokemonDetailsActivity> {
                    putInt(PokemonDetailsActivity.POKEMON_ID, pokemon.id)
                }
            }

            override fun onFavorite(pokemon: PokemonItem, isFavorite: Boolean) {
                Timber.d("Adapter onFavorite | isFavorite: $isFavorite, pokemon: $pokemon")
                // TODO: Favorite pokemon
            }
        }
    )

    override val showBackButton: Boolean = false
    override fun toolbarTitle(): Int = R.string.pokemons

    override fun onHomeMenuItemClicked(): Boolean {
        showNavDrawer()
        return true
    }

    override fun setupViews() {
        setupListeners()
        setupObservers()
        setupToolbarMenu()
        setupNavigationView()
    }

    override fun onFragmentVisible() {
        super.onFragmentVisible()
        showScreenLoading()
        activityActionBar.setHomeAsUpIndicator(context, coreUiR.drawable.ic_hamburger)
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
        root.show()
        result.second?.let {
            showToast(result.second ?: defaultErrorMessage)
            recyclerView.onLoadItemsError()
        }
        recyclerView.onLoadItemsSuccess(result.first.orEmpty())
    }

    private fun setupToolbarMenu() {
        setMenu(R.menu.pokemons_toolbar_menu, viewLifecycleOwner) { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    (menuItem.actionView as SearchView).setOnQueryTextChangeDebouncing(
                        onSubmit = ::onSearchQueryChange,
                        onTextChange = ::onSearchQueryChange
                    )
                    true
                }

                else -> false
            }
        }
    }

    private fun setupNavigationView() {
        activityNavigationView?.inflateMenu(R.menu.pokemons_nav_drawer)
        activityNavigationView?.setNavigationItemSelectedListener {
            closeNavDrawer()
            true
        }
    }

    private fun onSearchQueryChange(searchView: SearchView, query: String) = with(binding) {
        searchView.clearFocus()
        searchView.context.hideKeyboard()
        recyclerView.resetCurrentPage()
        showScreenLoading()
        root.hide()
        if (query.isEmpty()) viewModel.loadItems() else viewModel.searchPokemon(query)
    }

    companion object {
        fun newInstance() = PokemonsListFragment()
    }
}