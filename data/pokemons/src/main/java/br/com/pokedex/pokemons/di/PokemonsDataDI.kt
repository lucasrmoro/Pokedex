package br.com.pokedex.pokemons.di

import br.com.pokedex.core.ext.dispatcherIO
import br.com.pokedex.core_network.ext.retrofit
import br.com.pokedex.pokemons.mapper.PokemonMapper
import br.com.pokedex.pokemons.mapper.PokemonMapperImpl
import br.com.pokedex.pokemons.remote.PokemonsRemoteDataSource
import br.com.pokedex.pokemons.remote.PokemonsRemoteDataSourceImpl
import br.com.pokedex.pokemons.remote.PokemonsService
import br.com.pokedex.pokemons.repository.PokemonsRepository
import br.com.pokedex.pokemons.repository.PokemonsRepositoryImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val module = module {
    // Services
    single { retrofit.create(PokemonsService::class.java) }

    // Data Sources
    single<PokemonsRemoteDataSource> { PokemonsRemoteDataSourceImpl(pokemonsService = get()) }

    // Mappers
    single<PokemonMapper> { PokemonMapperImpl() }

    // Repositories
    single<PokemonsRepository> {
        PokemonsRepositoryImpl(
            pokemonsRemoteDataSource = get(),
            pokemonMapper = get(),
            dispatcher = dispatcherIO
        )
    }
}

object PokemonsDataModule {
    fun init() {
        loadKoinModules(module + pokemonsDomainModule)
    }
}