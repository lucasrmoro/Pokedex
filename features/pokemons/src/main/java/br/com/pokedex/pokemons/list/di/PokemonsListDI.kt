package br.com.pokedex.pokemons.list.di

import br.com.pokedex.core.ext.dispatcherIO
import br.com.pokedex.core_network.ext.retrofit
import br.com.pokedex.pokemons.list.data.remote.PokemonsRemoteDataSource
import br.com.pokedex.pokemons.list.data.remote.PokemonsRemoteDataSourceImpl
import br.com.pokedex.pokemons.list.data.remote.PokemonsService
import br.com.pokedex.pokemons.list.domain.mapper.PokemonMapper
import br.com.pokedex.pokemons.list.domain.mapper.PokemonMapperImpl
import br.com.pokedex.pokemons.list.domain.repository.PokemonsRepository
import br.com.pokedex.pokemons.list.domain.repository.PokemonsRepositoryImpl
import br.com.pokedex.pokemons.list.domain.useCase.get.GetPokemonDetailsUseCase
import br.com.pokedex.pokemons.list.domain.useCase.get.GetPokemonDetailsUseCaseImpl
import br.com.pokedex.pokemons.list.domain.useCase.get.GetPokemonsByNameUseCase
import br.com.pokedex.pokemons.list.domain.useCase.get.GetPokemonsByNameUseCaseImpl
import br.com.pokedex.pokemons.list.domain.useCase.getAll.GetAllPokemonsUseCase
import br.com.pokedex.pokemons.list.domain.useCase.getAll.GetAllPokemonsUseCaseImpl
import br.com.pokedex.pokemons.list.presentation.viewModel.PokemonsListViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val dataModule = module {
    // Remote
    single { retrofit.create(PokemonsService::class.java) }
    single<PokemonsRemoteDataSource> { PokemonsRemoteDataSourceImpl(pokemonsService = get()) }
}

private val domainModule = module {
    // Mapper
    single<PokemonMapper> { PokemonMapperImpl() }

    // Repository
    single<PokemonsRepository> {
        PokemonsRepositoryImpl(
            pokemonsRemoteDataSource = get(),
            pokemonMapper = get(),
            dispatcher = dispatcherIO
        )
    }

    // Use Case
    single<GetAllPokemonsUseCase> { GetAllPokemonsUseCaseImpl(pokemonsRepository = get()) }
    single<GetPokemonsByNameUseCase> { GetPokemonsByNameUseCaseImpl(pokemonsRepository = get()) }
    single<GetPokemonDetailsUseCase> { GetPokemonDetailsUseCaseImpl(pokemonsRepository = get()) }
}

private val viewModelModule = module {
    viewModel {
        PokemonsListViewModel(
            resourcesProvider = get(),
            getAllPokemonsUseCase = get(),
            getPokemonsByNameUseCase = get()
        )
    }
}

internal object PokemonsListModule {
    fun init() {
        loadKoinModules(listOf(dataModule, domainModule, viewModelModule))
    }
}