package br.com.pokedex.core.provider.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

interface NavigationProvider {

    val lastFragment: Fragment?
    val isLastFragment: Boolean

    fun navigate(
        fragment: Fragment,
        backStack: String,
        @IdRes containerId: Int,
        clearBackStack: Boolean,
        clearTop: Boolean
    )

    fun popBackStack()
    fun setOnCurrentFragmentChangeListener(block: (currentFragment: Fragment?) -> Unit)

}