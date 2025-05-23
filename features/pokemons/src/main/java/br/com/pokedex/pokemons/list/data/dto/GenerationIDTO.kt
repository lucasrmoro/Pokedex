package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class GenerationIDTO(
    @SerializedName("red-blue") val redBlue: PokemonRedBlueDTO?,
    @SerializedName("yellow") val yellow: PokemonYellowDTO?
)