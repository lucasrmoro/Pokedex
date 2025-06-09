package br.com.pokedex.pokemons.di

import br.com.pokedex.pokemons.useCase.ability.GetPokemonAbilityUseCase
import br.com.pokedex.pokemons.useCase.ability.GetPokemonAbilityUseCaseImpl
import br.com.pokedex.pokemons.useCase.details.GetPokemonDetailsUseCase
import br.com.pokedex.pokemons.useCase.details.GetPokemonDetailsUseCaseImpl
import br.com.pokedex.pokemons.useCase.list.get.GetPokemonsByNameUseCase
import br.com.pokedex.pokemons.useCase.list.get.GetPokemonsByNameUseCaseImpl
import br.com.pokedex.pokemons.useCase.list.getAll.GetAllPokemonsUseCase
import br.com.pokedex.pokemons.useCase.list.getAll.GetAllPokemonsUseCaseImpl
import org.koin.dsl.module

val pokemonsDomainModule = module {
    // Use Cases
    factory<GetPokemonDetailsUseCase> { GetPokemonDetailsUseCaseImpl(pokemonsRepository = get()) }
    factory<GetPokemonsByNameUseCase> { GetPokemonsByNameUseCaseImpl(pokemonsRepository = get()) }
    factory<GetAllPokemonsUseCase> { GetAllPokemonsUseCaseImpl(pokemonsRepository = get()) }
    factory<GetPokemonAbilityUseCase> { GetPokemonAbilityUseCaseImpl(pokemonsRepository = get()) }
}