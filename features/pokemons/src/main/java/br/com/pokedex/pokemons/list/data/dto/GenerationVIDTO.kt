package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class GenerationVIDTO(
    @SerializedName("omegaruby-alphasapphire") val omegaRubyAlphaSapphire: PokemonOmegarubyAlphasapphireDTO?,
    @SerializedName("x-y") val xY: XYDTO?
)