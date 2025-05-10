package br.com.pokedex.pokemons.remote

import br.com.pokedex.pokemons.dto.PokemonAbilitiesDTO
import br.com.pokedex.pokemons.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.dto.PokemonTypeDetailsDTO
import br.com.pokedex.pokemons.dto.PokemonsListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonsService {

    @GET(GET_POKEMONS)
    suspend fun getPokemons(@Query(OFFSET) offset: Int, @Query(LIMIT) limit: Int): PokemonsListDTO

    @GET(GET_POKEMON)
    suspend fun getPokemonDetails(@Path(ID_OR_NAME_PARAM) name: String): PokemonDetailsDTO

    @GET(GET_POKEMON)
    suspend fun getPokemonDetails(@Path(ID_OR_NAME_PARAM) id: Int): PokemonDetailsDTO

    @GET(GET_POKEMON_TYPE_DETAILS)
    suspend fun getPokemonTypeDetails(@Path(ID_OR_NAME_PARAM) typeName: String): PokemonTypeDetailsDTO

    @GET(GET_POKEMON_ABILITIES)
    suspend fun getPokemonAbilities(@Path(ID_OR_NAME_PARAM) name: String): PokemonAbilitiesDTO

    companion object {
        // Params
        private const val ID_OR_NAME_PARAM = "id_or_name"
        private const val OFFSET = "offset"
        private const val LIMIT = "limit"

        // Endpoints
        private const val GET_POKEMONS = "pokemon"
        private const val GET_POKEMON = "pokemon/{$ID_OR_NAME_PARAM}"
        private const val GET_POKEMON_TYPE_DETAILS = "type/{$ID_OR_NAME_PARAM}"
        private const val GET_POKEMON_ABILITIES = "ability/{$ID_OR_NAME_PARAM}"
    }
}