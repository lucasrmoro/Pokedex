package br.com.pokedex.core_ui.ext

import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmapOrNull
import androidx.palette.graphics.Palette

fun Drawable?.palette(block: Palette.() -> Unit): Unit? = this?.toBitmapOrNull()?.let { bitmap ->
    Palette.from(bitmap).generate { palette ->
        palette?.let { block.invoke(it) }
    }
    Unit
}