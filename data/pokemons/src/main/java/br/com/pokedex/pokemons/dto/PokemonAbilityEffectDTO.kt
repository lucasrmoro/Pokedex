package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class PokemonAbilityEffectDTO(
    @SerializedName("effect") val effect: String?,
    @SerializedName("language") val language: LanguageDTO?,
    @SerializedName("short_effect") val shortEffect: String?
)