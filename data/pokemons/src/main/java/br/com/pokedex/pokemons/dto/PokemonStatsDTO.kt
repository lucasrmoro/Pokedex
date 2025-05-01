package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class PokemonStatsDTO(
    @SerializedName("base_stat") val baseStat: Int?,
    @SerializedName("effort") val effort: Int?,
    @SerializedName("stat") val stat: StatDTO?
)