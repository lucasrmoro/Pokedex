package br.com.pokedex.core.exceptions

import com.google.gson.annotations.SerializedName

data class PokeAPIException(
    @SerializedName("Status") val status: Int? = null,
    @SerializedName("Message") val message: String? = null,
    @SerializedName("ExceptionCode") val exceptionCode: Int? = null
)