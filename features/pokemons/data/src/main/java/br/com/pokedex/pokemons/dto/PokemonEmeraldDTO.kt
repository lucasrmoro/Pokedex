package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

internal data class PokemonEmeraldDTO(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)