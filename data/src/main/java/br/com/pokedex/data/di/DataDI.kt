package br.com.pokedex.data.di

import br.com.pokedex.data.foo.local.FooLocalDataSource
import br.com.pokedex.data.foo.local.FooLocalDataSourceImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val fooModule = module {
    factory<FooLocalDataSource> { FooLocalDataSourceImpl(fooDao = get()) }
}

object DataModule {
    fun init() {
        loadKoinModules(fooModule)
    }
}