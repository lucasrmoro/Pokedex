package br.com.pokedex.app

import android.app.Application
import br.com.pokedex.BuildConfig
import br.com.pokedex.core.di.CoreModule
import br.com.pokedex.core_network.di.CoreNetworkModule
import br.com.pokedex.core_ui.di.CoreUiModule
import br.com.pokedex.data.di.DataModule
import br.com.pokedex.domain.di.DomainModule
import br.com.pokedex.local_storage.di.LocalStorageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setupKoin()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setupKoin() {
        startKoin {
            androidContext(applicationContext)
        }
        CoreModule.init()
        CoreUiModule.init()
        CoreNetworkModule.init()
        LocalStorageModule.init()
        DataModule.init()
        DomainModule.init()
    }

}