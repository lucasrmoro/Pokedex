package br.com.pokedex.core.ext

val Char.Companion.SPACE
    get() = ' '

val Char.Companion.DOT
    get() = '.'

val Char.Companion.COMMA
    get() = ','

val Char.Companion.SLASH
    get() = '/'

val Char.Companion.BACKSLASH
    get() = '\\'

val Char.Companion.PARENTHESES_OPEN
    get() = '('

val Char.Companion.PARENTHESES_CLOSE
    get() = ')'

val Char.Companion.HYPHEN
    get() = '-'

val Char.Companion.HASH
    get() = '#'

val Char.Companion.ASTERISK
    get() = '*'

val Char.Companion.SIGNS
    get() = listOf(
        Char.SPACE,
        Char.DOT,
        Char.COMMA,
        Char.SLASH,
        Char.BACKSLASH,
        Char.PARENTHESES_OPEN,
        Char.PARENTHESES_CLOSE,
        Char.HYPHEN,
        Char.HASH,
        Char.ASTERISK
    )

fun Char.isSign() = Char.SIGNS.contains(this)