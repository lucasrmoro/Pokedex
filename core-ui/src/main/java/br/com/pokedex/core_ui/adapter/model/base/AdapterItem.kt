package br.com.pokedex.core_ui.adapter.model.base

interface AdapterItem : DiffUtilEquality {

    val itemViewType: AdapterViewType

}