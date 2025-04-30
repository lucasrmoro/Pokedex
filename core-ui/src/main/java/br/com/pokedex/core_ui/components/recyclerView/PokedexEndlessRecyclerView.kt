package br.com.pokedex.core_ui.components.recyclerView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pokedex.core.ext.ONE
import br.com.pokedex.core_ui.adapter.PokedexGenericAdapter
import br.com.pokedex.domain.adapter.model.Loading
import br.com.pokedex.domain.adapter.AdapterItem
import br.com.pokedex.core_ui.components.recyclerView.PokedexEndlessRecyclerViewScrollListener.PokedexEndlessRecyclerViewScrollListenerCallbacks

class PokedexEndlessRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs), PokedexEndlessRecyclerViewScrollListenerCallbacks {

    private var callbacks: PokedexEndlessRecyclerViewCallbacks? = null

    private val pokedexGenericAdapter: PokedexGenericAdapter?
        get() = adapter as? PokedexGenericAdapter

    private val items: List<br.com.pokedex.domain.adapter.AdapterItem>
        get() = pokedexGenericAdapter?.currentList.orEmpty()

    var currentPage = Int.ONE
        private set

    override val hasItemsToLoad: Boolean
        get() = callbacks?.pagesCount?.let { currentPage <= it } ?: false

    private var _isLoading: Boolean = false
    override val isLoading: Boolean
        get() = _isLoading

    override fun loadItems() {
        showLoading {
            callbacks?.loadItems(currentPage)
        }
    }

    fun setup(adapter: PokedexGenericAdapter, callbacks: PokedexEndlessRecyclerViewCallbacks) {
        this.adapter = adapter
        this.callbacks = callbacks
        layoutManager = LinearLayoutManager(context)
        addOnScrollListener(PokedexEndlessRecyclerViewScrollListener(this))
    }

    fun <T : br.com.pokedex.domain.adapter.AdapterItem> onLoadItemsSuccess(newItems: List<T>) {
        removeLoading {
            pokedexGenericAdapter?.submitList(
                if (currentPage == Int.ONE) newItems else items.plus(newItems)
            )
            currentPage++
        }
    }

    fun onLoadItemsError() {
        removeLoading()
    }

    fun resetCurrentPage() {
        currentPage = Int.ONE
    }

    private fun showLoading(onLoadingShown: () -> Unit) {
        post {
            _isLoading = true
            pokedexGenericAdapter?.submitList(items.plus(br.com.pokedex.domain.adapter.model.Loading())) {
                onLoadingShown()
            }
        }
    }

    private fun removeLoading(onLoadingRemoved: () -> Unit = {}) {
        post {
            _isLoading = false
            pokedexGenericAdapter?.submitList(items.filter { it !is br.com.pokedex.domain.adapter.model.Loading }) {
                onLoadingRemoved()
            }
        }
    }
}