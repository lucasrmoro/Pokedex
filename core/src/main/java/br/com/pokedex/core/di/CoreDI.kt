package br.com.pokedex.core.di

import br.com.pokedex.core.provider.appSession.AppSessionProvider
import br.com.pokedex.core.provider.appSession.AppSessionProviderImpl
import br.com.pokedex.core.provider.navigation.NavigationProvider
import br.com.pokedex.core.provider.navigation.NavigationProviderImpl
import br.com.pokedex.core.provider.network.provider.NetworkConnectivityProvider
import br.com.pokedex.core.provider.network.provider.NetworkConnectivityProviderImpl
import br.com.pokedex.core.provider.permission.PermissionsProvider
import br.com.pokedex.core.provider.permission.PermissionsProviderImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val coreModule = module {
    single<CoroutineDispatcher>(DispatcherIO) { Dispatchers.IO }
    single<CoroutineDispatcher>(DispatcherMain) { Dispatchers.Main }
    single<CoroutineDispatcher>(DispatcherDefault) { Dispatchers.Default }
    single<NavigationProvider> { params -> NavigationProviderImpl(params.get()) }
    single<PermissionsProvider> { params -> PermissionsProviderImpl(params.get()) }
    single<AppSessionProvider> { AppSessionProviderImpl() }
    single<NetworkConnectivityProvider> { NetworkConnectivityProviderImpl(context = get()) }
}

object CoreModule {
    fun init() {
        loadKoinModules(coreModule)
    }
}