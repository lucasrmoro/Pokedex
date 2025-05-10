package br.com.pokedex.pokemons.dto

import com.google.gson.annotations.SerializedName

data class PokemonAbilitiesDTO(
    @SerializedName("effect_changes") val effectChanges: List<PokemonAbilityEffectDTO>?,
    @SerializedName("effect_entries") val effectEntries: List<PokemonAbilityEffectDTO>?,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntryDTO>?,
    @SerializedName("generation") val generation: GenerationDTO?,
    @SerializedName("id") val id: Int?,
    @SerializedName("is_main_series") val isMainSeries: Boolean,
    @SerializedName("name") val name: String?,
    @SerializedName("names") val names: List<NameDTO>?,
    @SerializedName("pokemon") val pokemons: List<AbilityDTO>?
)