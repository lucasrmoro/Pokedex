package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class GenerationIIIDTO(
    @SerializedName("emerald") val emerald: PokemonEmeraldDTO?,
    @SerializedName("firered-leafgreen") val fireRedLeafGreen: PokemonFireredLeafgreenDTO?,
    @SerializedName("ruby-sapphire") val rubySapphire: PokemonRubySapphireDTO?
)