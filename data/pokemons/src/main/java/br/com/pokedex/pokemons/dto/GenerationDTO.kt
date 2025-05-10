package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class GenerationDTO(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)