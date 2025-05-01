package br.com.pokedex.domain.adapter

interface AdapterItem : DiffUtilEquality {

    val itemViewType: AdapterViewType

}