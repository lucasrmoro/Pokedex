package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class PokemonsListDTO(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val pokemons: List<PokemonDTO>?
)