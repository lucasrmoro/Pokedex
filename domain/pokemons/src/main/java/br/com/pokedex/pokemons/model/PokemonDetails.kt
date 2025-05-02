package br.com.pokedex.pokemons.model

import android.graphics.Bitmap

data class PokemonDetails(
    val id: Int,
    val image: Bitmap,
    val name: String,
    val hp: Int,
    val atk: Int,
    val def: Int,
    val satk: Int,
    val sdef: Int,
    val spd: Int,
    val types: List<PokemonTypeItem>
)