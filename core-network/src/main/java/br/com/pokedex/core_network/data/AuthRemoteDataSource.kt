package br.com.pokedex.core_network.data

import br.com.pokedex.entities.remote.LoginDTO
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {

    suspend fun refreshToken(refreshToken: String): Flow<LoginDTO>

}