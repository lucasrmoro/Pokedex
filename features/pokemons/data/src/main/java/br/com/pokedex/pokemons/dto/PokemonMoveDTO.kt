package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

internal data class PokemonMoveDTO(
    @SerializedName("move") val move: MoveDTO?,
    @SerializedName("version_group_details") val versionGroupDetails: List<PokemonVersionGroupDetailDTO>?
)