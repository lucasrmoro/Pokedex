package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class DreamWorldDTO(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?
)