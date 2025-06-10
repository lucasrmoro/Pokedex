package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

internal data class GenerationVDTO(
    @SerializedName("black-white") val blackWhite: PokemonBlackWhiteDTO?
)