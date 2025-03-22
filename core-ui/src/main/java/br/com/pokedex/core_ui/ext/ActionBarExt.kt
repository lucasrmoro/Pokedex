package br.com.pokedex.core_ui.ext

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.content.res.AppCompatResources

fun ActionBar?.setHomeAsUpIndicator(context: Context?, @DrawableRes drawableRes: Int) {
    this?.setHomeAsUpIndicator(
        context?.let {
            AppCompatResources.getDrawable(it, drawableRes).also { setDisplayShowHomeEnabled(true) }
        }
    )
}