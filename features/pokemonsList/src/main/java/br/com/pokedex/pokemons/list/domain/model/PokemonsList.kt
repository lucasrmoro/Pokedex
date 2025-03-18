package br.com.pokedex.pokemons.list.domain.model

import br.com.pokedex.core_ui.adapter.model.PokemonItem

data class PokemonsList(
    val pagesCount: Int,
    val pokemons: List<PokemonItem>
)