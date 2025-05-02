package br.com.pokedex.core.ext

import br.com.pokedex.core.exceptions.PokeAPIException
import br.com.pokedex.core.exceptions.PokedexException
import com.google.gson.Gson
import retrofit2.HttpException

internal fun Throwable?.toPokedexException() = PokedexException(
    message = this?.message.orEmpty(),
    apiException = (this as? HttpException).toApiException()
)

private fun HttpException?.toApiException() = runCatching {
    Gson().fromJson(this?.response()?.errorBody()?.string().orEmpty(), PokeAPIException::class.java)
}.getOrElse { PokeAPIException() }