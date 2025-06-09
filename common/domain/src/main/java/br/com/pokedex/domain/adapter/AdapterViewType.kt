package br.com.pokedex.domain.adapter

enum class AdapterViewType {
    LOADING, POKEMON, POKEMON_TYPE, POKEMON_STAT, POKEMON_ABILITY;

    companion object {
        fun fromValue(value: Int) = entries.toTypedArray()[value]
    }
}