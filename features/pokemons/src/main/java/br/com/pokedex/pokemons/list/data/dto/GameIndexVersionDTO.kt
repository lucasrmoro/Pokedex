package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class GameIndexVersionDTO(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
)