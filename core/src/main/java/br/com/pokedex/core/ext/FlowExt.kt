package br.com.pokedex.core.ext

import br.com.pokedex.core.exceptions.PokedexException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform
import retrofit2.HttpException
import timber.log.Timber
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

fun <T> Flow<T>.onError(
    ignoreUnauthorized: Boolean = true,
    block: PokedexException.() -> Unit
): Flow<T> = catch {
    if (ignoreUnauthorized && (it as? HttpException)?.code() == HTTP_UNAUTHORIZED) {
        return@catch
    }
    Timber.e(it)

    block(it.toPokedexException())
}

suspend fun <T> Flow<T>.onSuccess(block: suspend T.() -> Unit) {
    collect { block(it) }
}

suspend fun Flow<*>.onFinish(block: suspend () -> Unit) {
    runCatching {
        collect { block.invoke() }
    }.onFailure {
        Timber.e(it)
        block.invoke()
    }
}

suspend fun Flow<*>.launch() {
    runCatching { collect {} }
}

suspend fun <T> Flow<T>.collectResult(): T {
    var result: T? = null
    collect {
        result = it
    }
    return result ?: throw Exception()
}

suspend fun <T> Flow<T>.collectResultSafely(): T? {
    var result: T? = null
    onError {
        result = null
    }.onSuccess {
        result = this
    }
    return result
}

fun <T, R> Flow<T>.update(block: suspend T.() -> R): Flow<R> = transform { emit(block(it)) }