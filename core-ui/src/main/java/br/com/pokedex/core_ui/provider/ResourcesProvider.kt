package br.com.pokedex.core_ui.provider

import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes stringRes: Int, vararg args: Any): String

}