package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

internal data class PokemonsListDTO(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val pokemons: List<PokemonDTO>?
)