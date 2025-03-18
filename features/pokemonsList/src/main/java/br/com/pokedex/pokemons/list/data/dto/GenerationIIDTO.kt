package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class GenerationIIDTO(
    @SerializedName("crystal") val crystal: PokemonCrystalDTO?,
    @SerializedName("gold") val gold: PokemonGoldDTO?,
    @SerializedName("silver") val silver: PokemonSilverDTO?
)