package br.com.pokedex.pokemons.model

data class PokemonListItems(
    val pagesCount: Int,
    val pokemons: List<PokemonItem>
)