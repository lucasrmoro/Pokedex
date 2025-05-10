package br.com.pokedex.pokemons.useCase.ability

import br.com.pokedex.pokemons.model.PokemonAbility
import br.com.pokedex.pokemons.repository.PokemonsRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonAbilityUseCaseImpl(
    private val pokemonsRepository: PokemonsRepository
) : GetPokemonAbilityUseCase {

    override suspend fun invoke(name: String): Flow<PokemonAbility> =
        pokemonsRepository.getPokemonAbility(name)

}