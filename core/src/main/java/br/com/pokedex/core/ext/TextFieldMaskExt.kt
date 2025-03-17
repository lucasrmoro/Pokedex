package br.com.pokedex.core.ext

val Char.Companion.MASK_SIGN
    get() = Char.HASH

val String.Companion.CELLPHONE_MASK
    get() = "(##) #####-####"

val String.Companion.DATE_MASK
    get() = "##/##/####"

fun String?.unmasked(): String = this?.let { originalText ->
    Char.SIGNS.fold(originalText) { text, sign ->
        text.replace(sign.toString(), String.EMPTY)
    }
}.orEmpty()