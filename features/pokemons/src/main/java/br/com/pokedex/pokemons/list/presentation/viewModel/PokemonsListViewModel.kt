package br.com.pokedex.pokemons.list.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core.ext.ONE
import br.com.pokedex.core.ext.TWENTY
import br.com.pokedex.core.ext.ZERO
import br.com.pokedex.core.ext.onError
import br.com.pokedex.core.ext.onSuccess
import br.com.pokedex.core_ui.adapter.model.PokemonItem
import br.com.pokedex.core_ui.components.recyclerView.PokedexEndlessRecyclerViewCallbacks
import br.com.pokedex.pokemons.list.domain.useCase.getAll.GetAllPokemonsUseCase
import kotlinx.coroutines.delay

class PokemonsListViewModel(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase
) : BaseViewModel(), PokedexEndlessRecyclerViewCallbacks {

    private val _onFetchPokemons = MutableLiveData<Pair<List<PokemonItem>?, String?>>()
    val onFetchPokemons: LiveData<Pair<List<PokemonItem>?, String?>> = _onFetchPokemons

    private var _pagesCount: Int = Int.ZERO
    override val pagesCount: Int
        get() = _pagesCount

    private val itemsPerPage = Int.TWENTY

    override fun loadItems(page: Int) {
        launch {
            getAllPokemonsUseCase(page = page, itemsPerPage = itemsPerPage).onError {
                _onFetchPokemons.postValue(null to message)
            }.onSuccess {
                _pagesCount = this.pagesCount
                _onFetchPokemons.postValue(this.pokemons to null)
            }
        }
    }

    fun loadItems() {
        launch {
            delay(10000L)
            loadItems(Int.ONE)
        }
    }
}