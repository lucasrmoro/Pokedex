package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class PokemonTypeDTO(
    @SerializedName("slot") val slot: Int?,
    @SerializedName("type") val type: TypeDTO?
)