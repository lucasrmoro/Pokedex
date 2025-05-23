package br.com.pokedex.pokemons.list.data.dto

import com.google.gson.annotations.SerializedName

data class GenerationVIIDTO(
    @SerializedName("icons") val icons: IconsDTO?,
    @SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: PokemonUltraSunUltraMoonDTO?
)