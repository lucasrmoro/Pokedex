package br.com.pokedex.core_ui.components.recyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pokedex.core.ext.THREE
import br.com.pokedex.core.ext.ZERO
import br.com.pokedex.core.ext.orZero

internal class PokedexEndlessRecyclerViewScrollListener(
    private val callbacks: PokedexEndlessRecyclerViewScrollListenerCallbacks
) : RecyclerView.OnScrollListener() {

    private val threshold = Int.THREE

    override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(rv, dx, dy)

        with(callbacks) {
            if (isLoading.not() && hasItemsToLoad && rv.isLastItemVisible() && dy >= Int.ZERO) {
                loadItems()
            }
        }
    }

    private fun RecyclerView.isLastItemVisible(): Boolean {
        val layoutManager = (this.layoutManager as? LinearLayoutManager)
        val visibleItemPositionToLoadMoreItems = adapter?.itemCount?.minus(threshold).orZero()

        return layoutManager?.findLastCompletelyVisibleItemPosition()
            .orZero() >= visibleItemPositionToLoadMoreItems
    }

    internal interface PokedexEndlessRecyclerViewScrollListenerCallbacks {

        val hasItemsToLoad: Boolean
        val isLoading: Boolean

        fun loadItems()

    }
}