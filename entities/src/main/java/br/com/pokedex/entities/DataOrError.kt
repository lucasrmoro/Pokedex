package br.com.pokedex.entities

data class DataOrError<out A, out B>(
    val data: A? = null,
    val error: B? = null
)