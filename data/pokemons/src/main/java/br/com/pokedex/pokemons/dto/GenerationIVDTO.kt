package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class GenerationIVDTO(
    @SerializedName("diamond-pearl") val diamondPearl: PokemonDiamondPearlDTO?,
    @SerializedName("heartgold-soulsilver") val heartGoldSoulSilver: PokemonHeartgoldSoulsilverDTO?,
    @SerializedName("platinum") val platinum: PokemonPlatinumDTO?
)