package br.com.pokedex.core_ui.ext

import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.core.graphics.ColorUtils
import br.com.pokedex.core.ext.ONE_HUNDRED
import br.com.pokedex.core.ext.TWO_HUNDRED_FIFTY_FIVE

fun @receiver:ColorInt Int.withAlpha(@IntRange(from = 0, to = 100) alpha: Int) =
    ColorUtils.setAlphaComponent(
        this,
        (alpha / Double.ONE_HUNDRED * Int.TWO_HUNDRED_FIFTY_FIVE).toInt()
    )