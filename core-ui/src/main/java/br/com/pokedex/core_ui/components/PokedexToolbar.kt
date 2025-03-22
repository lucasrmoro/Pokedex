package br.com.pokedex.core_ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import br.com.pokedex.core_ui.databinding.PokedexToolbarBinding
import br.com.pokedex.core_ui.delegate.ViewDelegate

class PokedexToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs) {

    private val binding =
        PokedexToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    val androidToolbar by ViewDelegate(binding.androidToolbar)

}