package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class PokemonTypeDetailsDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("damage_relations") val damageRelations: DamageRelationsDTO?,
    @SerializedName("moves") val moves: List<MoveDTO>?
)