package br.com.pokedex.core_network.data

import br.com.pokedex.core.base.dataSource.BaseDataSource
import br.com.pokedex.entities.remote.LoginDTO
import kotlinx.coroutines.flow.Flow

internal class AuthRemoteDataSourceImpl(
    private val authService: AuthService
) : AuthRemoteDataSource, BaseDataSource() {

    override suspend fun refreshToken(refreshToken: String): Flow<LoginDTO> = call {
        authService.refreshToken(refreshToken)
    }

}