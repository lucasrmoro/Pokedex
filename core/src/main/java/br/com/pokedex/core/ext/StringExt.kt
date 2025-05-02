package br.com.pokedex.core.ext

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
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

suspend fun String?.downloadImage(dispatcher: CoroutineDispatcher): Bitmap? {
    if (this.isNullOrBlank()) return null

    return withContext(dispatcher) {
        runCatching {
            val client = OkHttpClient()
            val request = Request.Builder().url(this@downloadImage).build()

            client.newCall(request).execute().use { response ->
                if (response.isSuccessful.not()) {
                    return@runCatching null
                }

                response.body()?.byteStream()?.use { inputStream ->
                    return@withContext BitmapFactory.decodeStream(inputStream)
                }
            }
        }.getOrNull()
    }
}