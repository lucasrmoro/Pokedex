package br.com.pokedex.pokemons.useCase.ability

import br.com.pokedex.pokemons.model.PokemonAbility
import kotlinx.coroutines.flow.Flow

interface GetPokemonAbilityUseCase {

    suspend operator fun invoke(name: String): Flow<PokemonAbility>

}