package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class OtherDTO(
    @SerializedName("dream_world") val dreamWorld: DreamWorldDTO?,
    @SerializedName("home") val home: HomeDTO?,
    @SerializedName("official_artwork") val officialArtwork: OfficialArtworkDTO?,
    @SerializedName("showdown") val showdown: ShowdownDTO?
)