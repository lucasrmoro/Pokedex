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
    get() = (ZERO..MAX_VALUE).random()

/**
 * Double
 */

val Double.Companion.ZERO
    get() = 0.0

val Double.Companion.ZERO_DOT_ONE
    get() = 0.1

val Double.Companion.ZERO_DOT_FIVE
    get() = 0.5

val Double.Companion.ZERO_DOT_NINE
    get() = 0.9

val Double.Companion.ONE
    get() = 1.0

val Double.Companion.TWO
    get() = 2.0

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

val Long.Companion.ONE_SECOND_IN_MILLIS
    get() = 1_000L

val Long.Companion.TWO_SECONDS_IN_MILLIS
    get() = 2_000L

/**
 * Float
 */

val Float.Companion.ONE
    get() = 1f
