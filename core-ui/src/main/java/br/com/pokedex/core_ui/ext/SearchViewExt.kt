package br.com.pokedex.core_ui.ext

import androidx.appcompat.widget.SearchView


fun SearchView.setOnQueryTextChange(
    onSubmit: (String) -> Unit = {},
    onTextChange: (String) -> Unit
) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(text: String?): Boolean {
            onSubmit(text.orEmpty())
            return false
        }

        override fun onQueryTextChange(text: String?): Boolean {
            onTextChange(text.orEmpty())
            return false
        }
    })
}