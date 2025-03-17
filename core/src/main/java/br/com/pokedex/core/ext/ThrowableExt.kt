package br.com.pokedex.core.ext

import br.com.pokedex.entities.PokeAPIException
import br.com.pokedex.entities.PokedexException
import com.google.gson.Gson
import retrofit2.HttpException

internal fun Throwable?.toPokedexException() = PokedexException(
    message = this?.message.orEmpty(),
    pokeAPIException = (this as? HttpException).toApiException()
)

private fun HttpException?.toApiException() = runCatching {
    Gson().fromJson(this?.response()?.errorBody()?.string().orEmpty(), PokeAPIException::class.java)
}.getOrElse { PokeAPIException() }

