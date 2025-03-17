package br.com.pokedex.core.ext

/**
 * Integer
 */

val Int.Companion.MINUS_ONE
    get() = -1

val Int.Companion.ZERO
    get() = 0

val Int.Companion.ONE
    get() = 1

val Int.Companion.TWO
    get() = 2

val Int.Companion.THREE
    get() = 3

val Int.Companion.ONE_HUNDRED
    get() = 100

fun Int?.orZero() = this ?: Int.ZERO

/**
 * Double
 */

val Double.Companion.ZERO
    get() = 0.0

/**
 * Long
 */

val Long.Companion.ZERO
    get() = 0L

val Long.Companion.FIVE
    get() = 5L