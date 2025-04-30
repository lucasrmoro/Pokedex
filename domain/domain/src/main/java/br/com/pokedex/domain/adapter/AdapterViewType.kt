package br.com.pokedex.domain.adapter

enum class AdapterViewType {
    LOADING, POKEMON;

    companion object {
        fun fromValue(value: Int) = entries.toTypedArray()[value]
    }
}