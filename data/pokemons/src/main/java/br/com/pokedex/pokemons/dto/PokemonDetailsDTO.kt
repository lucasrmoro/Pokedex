package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class PokemonDetailsDTO(
    @SerializedName("abilities") val abilities: List<PokemonAbilityDTO>?,
    @SerializedName("base_experience") val baseExperience: Int?,
    @SerializedName("criesDTO") val criesDTO: CriesDTO?,
    @SerializedName("forms") val forms: List<FormDTO>?,
    @SerializedName("game_indices") val gameIndices: List<GameIndexDTO>?,
    @SerializedName("height") val height: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("is_default") val isDefault: Boolean?,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String?,
    @SerializedName("moves") val moves: List<PokemonMoveDTO>?,
    @SerializedName("name") val name: String?,
    @SerializedName("order") val order: Int?,
    @SerializedName("species") val species: SpecieDTO?,
    @SerializedName("sprites") val sprites: SpritesDTO?,
    @SerializedName("stats") val stats: List<PokemonStatsDTO>?,
    @SerializedName("types") val types: List<PokemonTypeDTO>?,
    @SerializedName("weight") val weight: Int?
)