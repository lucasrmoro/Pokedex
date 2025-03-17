package br.com.pokedex.entities

import com.google.gson.annotations.SerializedName

data class PokeAPIException(
    @SerializedName("Status") val status: Int? = null,
    @SerializedName("Message") val message: String? = null,
    @SerializedName("ExceptionCode") val exceptionCode: Int? = null
)