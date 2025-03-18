package br.com.pokedex.core.ext

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

/**
 * Char
 */
val Char.Companion.BAR
    get() = '/'

val Char.Companion.ZERO
    get() = '0'

/**
 * String
 */

val String.Companion.EMPTY
    get() = ""

val String.Companion.SPACE
    get() = " "

val String.Companion.BAR
    get() = "/"

val String.Companion.HASH
    get() = "#"

fun String?.toMoney(fractionDigits: Int = Int.TWO) = NumberFormat.getCurrencyInstance().apply {
    DecimalFormatSymbols.getInstance().also {
        it.currencySymbol = String.EMPTY
        (this as? DecimalFormat)?.decimalFormatSymbols = it
        maximumFractionDigits = fractionDigits
        minimumFractionDigits = fractionDigits
    }
}.format(this)

fun String?.plusSpacing(vararg others: String?) =
    this.orEmpty().plus(String.SPACE)
        .plus(others.filterNot { it.isNullOrEmpty().not() }
            .joinToString(separator = String.SPACE))

