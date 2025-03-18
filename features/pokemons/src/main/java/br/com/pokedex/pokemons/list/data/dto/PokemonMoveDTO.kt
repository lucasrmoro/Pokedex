package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class PokemonMoveDTO(
    @SerializedName("move") val move: MoveDTO?,
    @SerializedName("version_group_details") val versionGroupDetails: List<PokemonVersionGroupDetailDTO>?
)