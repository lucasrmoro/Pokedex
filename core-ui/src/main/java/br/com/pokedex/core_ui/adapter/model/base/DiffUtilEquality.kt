package br.com.pokedex.core_ui.adapter.model.base

interface DiffUtilEquality {

    fun areItemsTheSame(toCompare: Any): Boolean
    fun areContentsTheSame(toCompare: Any): Boolean = this == toCompare

}