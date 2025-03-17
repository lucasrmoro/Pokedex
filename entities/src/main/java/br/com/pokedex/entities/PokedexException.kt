package br.com.pokedex.entities

class PokedexException(
    override val message: String?,
    val pokeAPIException: PokeAPIException = PokeAPIException()
) : Throwable()