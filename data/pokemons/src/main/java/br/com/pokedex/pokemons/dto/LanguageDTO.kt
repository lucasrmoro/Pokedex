package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class LanguageDTO(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)