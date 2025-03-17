package br.com.pokedex.core_network.interceptor

import br.com.pokedex.core.ext.plusSpacing
import br.com.pokedex.core_network.NetworkConstants.AUTHORIZATION_HEADER
import br.com.pokedex.core_network.NetworkConstants.BEARER_TOKEN_TYPE
import br.com.pokedex.local_storage.prefs.PreferencesDataSource
import br.com.pokedex.local_storage.prefs.PreferencesDataSource.Companion.ACCESS_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(
    private val preferencesDataSource: PreferencesDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = preferencesDataSource.getString(ACCESS_TOKEN).orEmpty()
        val request = chain.request().newBuilder().header(
            AUTHORIZATION_HEADER, BEARER_TOKEN_TYPE.plusSpacing(accessToken)
        ).build()
        return chain.proceed(request)
    }

}