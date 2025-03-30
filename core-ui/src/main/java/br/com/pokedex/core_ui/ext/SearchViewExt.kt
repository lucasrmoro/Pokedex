package br.com.pokedex.core_ui.ext

import androidx.appcompat.widget.SearchView
import br.com.pokedex.core.ext.TWO_SECONDS_IN_MILLIS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


fun SearchView.setOnQueryTextChangeDebouncing(
    debouncingTimeInMillis: Long = Long.TWO_SECONDS_IN_MILLIS,
    onSubmit: SearchView.(String) -> Unit = {},
    onTextChange: SearchView.(String) -> Unit
) {
    var isSearchViewOpening = true
    val scope = CoroutineScope(Dispatchers.Main)
    var searchJob: Job? = null
    val search: (String, isTextSubmit: Boolean) -> Boolean = { text, isSubmit ->
        searchJob?.cancel()
        Timber.d("isSearchViewOpening: $isSearchViewOpening, text: $text")

        when {
            isSearchViewOpening -> isSearchViewOpening = false
            else -> searchJob = scope.launch {
                delay(debouncingTimeInMillis)
                if (isSubmit) onSubmit(text) else onTextChange(text)
            }
        }
        true
    }

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(text: String?): Boolean = search(text.orEmpty(), true)

        override fun onQueryTextChange(text: String?): Boolean = search(text.orEmpty(), false)
    })
}