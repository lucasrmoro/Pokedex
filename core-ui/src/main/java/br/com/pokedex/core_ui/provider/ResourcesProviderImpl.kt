package br.com.pokedex.core_ui.provider

import android.content.res.Resources

internal class ResourcesProviderImpl(private val resources: Resources) : ResourcesProvider {

    override fun getString(stringRes: Int, vararg args: Any): String =
        resources.getString(stringRes, *args)

}