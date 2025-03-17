package br.com.pokedex.core_ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.pokedex.core_ui.delegate.ViewDelegate
import br.com.pokedex.core_ui.databinding.PokedexToolbarBinding

class PokedexToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs) {

    private val binding =
        PokedexToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    val androidToolbar by ViewDelegate(binding.androidToolbar)

    fun setupWithNavController(
        navController: NavController,
        configuration: AppBarConfiguration = AppBarConfiguration(navController.graph)
    ) {
        androidToolbar.setupWithNavController(navController, configuration)
    }

    fun addMenuProvider(
        provider: MenuProvider,
        owner: LifecycleOwner,
        state: Lifecycle.State
    ) {
        androidToolbar.addMenuProvider(provider, owner, state)
    }


    fun setNavigationOnClickListener(onClick: View.() -> Unit) {
        androidToolbar.setNavigationOnClickListener {
            onClick.invoke(it)
        }
    }

}