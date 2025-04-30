package br.com.pokedex.core.exceptions

class PokedexException(
    override val message: String?,
    val apiException: PokeAPIException = PokeAPIException()
) : Throwable()