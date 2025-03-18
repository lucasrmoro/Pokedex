package br.com.pokedex.pokemons.list.data.remote

import br.com.pokedex.pokemons.list.data.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.list.data.dto.PokemonsListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokemonsService {

    @GET(GET_POKEMONS)
    suspend fun getPokemons(@Query(OFFSET) offset: Int, @Query(LIMIT) limit: Int): PokemonsListDTO

    @GET(GET_POKEMON)
    suspend fun getPokemonDetails(@Path(NAME_PARAM) name: String): PokemonDetailsDTO

    companion object {
        // Params
        private const val NAME_PARAM = "name"
        private const val OFFSET = "offset"
        private const val LIMIT = "limit"

        // Endpoints
        private const val GET_POKEMONS = "pokemon"
        private const val GET_POKEMON = "pokemon/{$NAME_PARAM}"
    }
}