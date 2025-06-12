package br.com.pokedex.domain.adapter

enum class AdapterViewType {
    LOADING, POKEMON, POKEMON_TYPE, POKEMON_STAT, POKEMON_ABILITY, POKEMON_EVOLUTION;

    companion object {
        fun fromValue(value: Int) = entries.toTypedArray()[value]
    }
}