package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class GenerationVDTO(
    @SerializedName("black-white") val blackWhite: PokemonBlackWhiteDTO?
)