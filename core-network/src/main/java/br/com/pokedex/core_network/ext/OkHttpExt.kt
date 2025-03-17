package br.com.pokedex.core_network.ext

import br.com.pokedex.core_network.NetworkConstants.REQUEST_TIMEOUT_IN_SECONDS
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

internal fun OkHttpClient.Builder.setTimeouts() =
    callTimeout(REQUEST_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(REQUEST_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)