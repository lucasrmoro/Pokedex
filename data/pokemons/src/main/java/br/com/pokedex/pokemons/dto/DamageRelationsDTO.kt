package br.com.pokedex.pokemons.dto


import com.google.gson.annotations.SerializedName

data class DamageRelationsDTO(
    @SerializedName("double_damage_from") val doubleDamageFrom: List<DamageRelationDTO>?,
    @SerializedName("double_damage_to") val doubleDamageTo: List<DamageRelationDTO>?,
    @SerializedName("half_damage_from") val halfDamageFrom: List<DamageRelationDTO>?,
    @SerializedName("half_damage_to") val halfDamageTo: List<DamageRelationDTO>?,
    @SerializedName("no_damage_from") val noDamageFrom: List<DamageRelationDTO>?,
    @SerializedName("no_damage_to") val noDamageTo: List<DamageRelationDTO>?
)