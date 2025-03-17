package br.com.pokedex.core_network.authenticator

import br.com.pokedex.core.ext.collectResultSafely
import br.com.pokedex.core.ext.plusSpacing
import br.com.pokedex.core.provider.appSession.AppSessionProvider
import br.com.pokedex.core_network.NetworkConstants.AUTHORIZATION_HEADER
import br.com.pokedex.core_network.NetworkConstants.BEARER_TOKEN_TYPE
import br.com.pokedex.core_network.data.AuthRemoteDataSource
import br.com.pokedex.local_storage.prefs.PreferencesDataSource
import br.com.pokedex.local_storage.prefs.PreferencesDataSource.Companion.ACCESS_TOKEN
import br.com.pokedex.local_storage.prefs.PreferencesDataSource.Companion.REFRESH_TOKEN
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Response
import okhttp3.Route

internal class NetworkAuthenticator(
    private val appSessionProvider: AppSessionProvider,
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : Authenticator {

    private val localAccessToken: String
        get() = preferencesDataSource.getString(ACCESS_TOKEN).orEmpty()

    override fun authenticate(route: Route?, response: Response) =
        synchronized(this) {
            val shouldRefreshAccessToken = response.shouldRefreshAccessToken()
            val accessToken = if (shouldRefreshAccessToken) {
                runBlocking { getRemoteAccessToken() }
            } else {
                localAccessToken
            }

            when {
                accessToken.isEmpty() || accessToken == FETCH_REMOTE_REFRESH_TOKEN_ERROR -> {
                    if (shouldRefreshAccessToken && localAccessToken != accessToken) {
                        preferencesDataSource.putString(ACCESS_TOKEN, accessToken, encrypt = true)
                        appSessionProvider.expireSession()
                    }
                    null
                }

                else -> {
                    preferencesDataSource.putString(ACCESS_TOKEN, accessToken, encrypt = true)
                    response.request.newBuilder()
                        .header(AUTHORIZATION_HEADER, accessToken.plusBearerTokenType())
                        .build()
                }
            }
        }

    private suspend fun getRemoteAccessToken(): String =
        authRemoteDataSource.refreshToken(preferencesDataSource.getString(REFRESH_TOKEN).orEmpty())
            .collectResultSafely()?.accessToken ?: FETCH_REMOTE_REFRESH_TOKEN_ERROR

    private fun Response.shouldRefreshAccessToken(): Boolean =
        request.header(AUTHORIZATION_HEADER) == localAccessToken

    private fun String.plusBearerTokenType() = BEARER_TOKEN_TYPE.plusSpacing(this)

    companion object {
        private const val FETCH_REMOTE_REFRESH_TOKEN_ERROR = "FETCH_REMOTE_REFRESH_TOKEN_ERROR"
    }
}