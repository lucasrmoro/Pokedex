package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

internal data class GenerationVIIIDTO(
    @SerializedName("icons") val icons: IconsDTO?
)