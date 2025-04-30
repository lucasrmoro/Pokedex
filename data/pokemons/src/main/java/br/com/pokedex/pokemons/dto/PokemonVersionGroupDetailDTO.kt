package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class PokemonVersionGroupDetailDTO(
    @SerializedName("level_learned_at") val levelLearnedAt: Int?,
    @SerializedName("move_learn_method") val moveLearnMethod: MoveLearnMethodDTO?,
    @SerializedName("order") val order: Int?,
    @SerializedName("version_group") val versionGroup: VersionGroupDTO?
)