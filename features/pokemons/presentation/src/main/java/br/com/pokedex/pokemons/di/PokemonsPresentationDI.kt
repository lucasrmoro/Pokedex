package br.com.pokedex.pokemons.di

import br.com.pokedex.pokemons.details.di.pokemonsDetailsModule
import br.com.pokedex.pokemons.list.di.pokemonsListModule

val pokemonsPresentationModules = pokemonsDetailsModule + pokemonsListModule