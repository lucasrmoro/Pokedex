package br.com.pokedex.core.exceptions

class PokedexException(
    override val message: String?,
    val pokeAPIException: PokeAPIException = PokeAPIException()
) : Throwable()