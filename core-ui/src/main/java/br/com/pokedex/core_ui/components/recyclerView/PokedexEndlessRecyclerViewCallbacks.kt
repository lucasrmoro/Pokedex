package br.com.pokedex.core_ui.components.recyclerView

interface PokedexEndlessRecyclerViewCallbacks {

    val pagesCount: Int?
    fun loadItems(page: Int)

}