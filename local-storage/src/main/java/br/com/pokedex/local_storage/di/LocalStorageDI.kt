package br.com.pokedex.local_storage.di

import androidx.room.Room
import br.com.pokedex.local_storage.db.LocalDatabase
import br.com.pokedex.local_storage.db.dao.FooDao
import br.com.pokedex.local_storage.ext.database
import br.com.pokedex.local_storage.prefs.PreferencesDataSource
import br.com.pokedex.local_storage.prefs.PreferencesDataSourceImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private const val ROOM_DATA_BASE_NAME = "pokedex_data_base"

private val localStorageModule = module {
    single<LocalDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = LocalDatabase::class.java,
            name = ROOM_DATA_BASE_NAME
        ).build()
    }
    single<PreferencesDataSource> { PreferencesDataSourceImpl(context = get()) }
}

private val daoModule = module {
    factory<FooDao> { database.fooDao() }
}

object LocalStorageModule {
    fun init() {
        loadKoinModules(listOf(localStorageModule, daoModule))
    }
}