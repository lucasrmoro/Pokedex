package br.com.pokedex.domain.adapter

interface DiffUtilEquality {

    fun areItemsTheSame(toCompare: Any): Boolean
    fun areContentsTheSame(toCompare: Any): Boolean = this == toCompare

}