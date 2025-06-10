package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

internal data class FormDTO(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)