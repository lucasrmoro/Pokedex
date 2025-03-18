package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class CriesDTO(
    @SerializedName("latest") val latest: String?,
    @SerializedName("legacy") val legacy: String?
)