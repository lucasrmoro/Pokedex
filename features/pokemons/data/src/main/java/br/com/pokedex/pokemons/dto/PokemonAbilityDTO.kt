package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

internal data class PokemonAbilityDTO(
    @SerializedName("ability") val ability: AbilityDTO?,
    @SerializedName("is_hidden") val isHidden: Boolean?,
    @SerializedName("slot") val slot: Int?
)