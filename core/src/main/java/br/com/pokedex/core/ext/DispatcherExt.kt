package br.com.pokedex.core.ext

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

suspend fun <T> CoroutineDispatcher.execute(block: suspend CoroutineScope.() -> T) =
    withContext(this, block)