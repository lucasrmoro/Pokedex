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

val Int.Companion.FOUR
    get() = 4

val Int.Companion.TWENTY
    get() = 20

val Int.Companion.THIRTY
    get() = 30

val Int.Companion.ONE_HUNDRED
    get() = 100

val Int.Companion.TWO_HUNDRED_FIFTY_FIVE
    get() = 255

fun Int?.orZero() = this ?: Int.ZERO

val Int.Companion.random
    get() = (Int.ZERO..Int.MAX_VALUE).random()

/**
 * Double
 */

val Double.Companion.ZERO
    get() = 0.0

val Double.Companion.ONE_HUNDRED
    get() = 100.0

/**
 * Long
 */

val Long.Companion.ZERO
    get() = 0L

val Long.Companion.FIVE
    get() = 5L

val Long.Companion.ONE_HUNDRED
    get() = 100L

val Long.Companion.TWO_SECONDS_IN_MILLIS
    get() = 2_000L

/**
 * Float
 */

val Float.Companion.ONE
    get() = 1f
