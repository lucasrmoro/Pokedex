package br.com.pokedex.entities.remote

import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("AccessToken") val accessToken: String?,
    @SerializedName("RefreshToken") val refreshToken: String?
)