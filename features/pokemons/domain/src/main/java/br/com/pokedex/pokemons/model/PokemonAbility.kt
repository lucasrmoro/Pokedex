package br.com.pokedex.pokemons.model

import br.com.pokedex.core.ext.EMPTY

data class PokemonAbility(
    val name: String,
    val description: String = String.EMPTY
)