package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class VersionGroupDTO(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)