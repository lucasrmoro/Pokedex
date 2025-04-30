package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class VersionsDTO(
    @SerializedName("generation-i") val generationI: GenerationIDTO?,
    @SerializedName("generation-ii") val generationII: GenerationIIDTO?,
    @SerializedName("generation-iii") val generationIII: GenerationIIIDTO?,
    @SerializedName("generation-iv") val generationIV: GenerationIVDTO?,
    @SerializedName("generation-v") val generationV: GenerationVDTO?,
    @SerializedName("generation-vi") val generationVI: GenerationVIDTO?,
    @SerializedName("generation-vii") val generationVII: GenerationVIIDTO?,
    @SerializedName("generation-viii") val generationVIII: GenerationVIIIDTO?
)