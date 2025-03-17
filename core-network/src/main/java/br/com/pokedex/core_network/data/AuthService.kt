package br.com.pokedex.core_network.data

import br.com.pokedex.entities.remote.LoginDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface AuthService {

    @GET(REFRESH_TOKEN)
    suspend fun refreshToken(@Path(REFRESH_TOKEN_PARAM) refreshToken: String): LoginDTO

    companion object {
        // Params
        private const val REFRESH_TOKEN_PARAM = "refreshToken"

        // Endpoints
        private const val REFRESH_TOKEN = "auth/refresh-token/{$REFRESH_TOKEN_PARAM}"
    }
}