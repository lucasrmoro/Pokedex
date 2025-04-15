package br.com.pokedex.core_ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import br.com.pokedex.core_ui.databinding.PokedexCircularLoadingBinding

class PokedexCircularLoading @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ProgressBar(context, attrs) {

    init {
        PokedexCircularLoadingBinding.inflate(LayoutInflater.from(context))
    }

}