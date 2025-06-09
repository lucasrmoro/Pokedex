package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

internal data class CriesDTO(
    @SerializedName("latest") val latest: String?,
    @SerializedName("legacy") val legacy: String?
)