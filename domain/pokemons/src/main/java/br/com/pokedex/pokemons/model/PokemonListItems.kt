package br.com.pokedex.pokemons.model

import br.com.pokedex.domain.adapter.model.pokemons.PokemonItem

data class PokemonListItems(
    val pagesCount: Int,
    val pokemons: List<PokemonItem>
)