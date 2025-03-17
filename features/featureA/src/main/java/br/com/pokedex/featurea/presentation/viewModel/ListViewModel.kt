package br.com.pokedex.featurea.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core.ext.ONE
import br.com.pokedex.core.ext.ONE_HUNDRED
import br.com.pokedex.core.ext.onError
import br.com.pokedex.core.ext.onSuccess
import br.com.pokedex.core_ui.adapter.model.Foo
import br.com.pokedex.core_ui.components.recyclerView.PokedexEndlessRecyclerViewCallbacks
import br.com.pokedex.domain.repository.foo.FooRepository
import kotlinx.coroutines.async

class ListViewModel(
    private val fooRepository: FooRepository
) : BaseViewModel(), PokedexEndlessRecyclerViewCallbacks {

    private val _onLoadItems = MutableLiveData<Pair<List<Foo>?, String?>>()
    val onLoadItems: LiveData<Pair<List<Foo>?, String?>> = _onLoadItems

    private var _pagesCount: Int = Int.ONE
    override val pagesCount: Int
        get() = _pagesCount

    private val itemsPerPage = Int.ONE_HUNDRED

    override fun loadItems(page: Int) {
        launch {
            fooRepository.getAll(page = page, itemsPerPage = itemsPerPage).onError {
                _onLoadItems.postValue(null to message)
            }.onSuccess {
                _onLoadItems.postValue(this to null)
            }
        }
    }

    fun loadItems() {
        launch {
            async { _pagesCount = fooRepository.getPagesCount(itemsPerPage = itemsPerPage) }.await()
            loadItems(Int.ONE)
        }
    }
}