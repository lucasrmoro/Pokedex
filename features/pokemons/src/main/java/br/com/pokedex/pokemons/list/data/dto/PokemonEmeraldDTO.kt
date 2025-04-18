package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class PokemonEmeraldDTO(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)