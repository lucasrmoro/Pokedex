package br.com.pokedex.core_ui.di

import br.com.pokedex.core_ui.provider.ResourcesProvider
import br.com.pokedex.core_ui.provider.ResourcesProviderImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val providerModule = module {
    single<ResourcesProvider> { ResourcesProviderImpl(resources = get()) }
}

object CoreUiModule {
    fun init() {
        loadKoinModules(providerModule)
    }
}