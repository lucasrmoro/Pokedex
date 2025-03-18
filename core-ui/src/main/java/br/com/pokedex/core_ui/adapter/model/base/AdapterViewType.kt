package br.com.pokedex.core_ui.adapter.model.base

enum class AdapterViewType {
    LOADING, FOO, POKEMON;

    companion object {
        fun fromValue(value: Int) = entries.toTypedArray()[value]
    }
}