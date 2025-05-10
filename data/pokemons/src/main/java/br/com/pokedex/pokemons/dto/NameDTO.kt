package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class NameDTO(
    @SerializedName("language") val language: LanguageDTO?,
    @SerializedName("name") val name: String?
)