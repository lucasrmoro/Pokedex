package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class IconsDTO(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?
)