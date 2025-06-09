package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

internal data class FlavorTextEntryDTO(
    @SerializedName("flavor_text") val flavorText: String?,
    @SerializedName("language") val language: LanguageDTO?,
    @SerializedName("version_group") val versionGroup: VersionGroupDTO?
)
