package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

internal data class GenerationVIDTO(
    @SerializedName("omegaruby-alphasapphire") val omegaRubyAlphaSapphire: PokemonOmegarubyAlphasapphireDTO?,
    @SerializedName("x-y") val xY: XYDTO?
)