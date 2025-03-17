package br.com.pokedex.featurea.di

import br.com.pokedex.featurea.presentation.viewModel.FeatureAViewModel
import br.com.pokedex.featurea.presentation.viewModel.FormViewModel
import br.com.pokedex.featurea.presentation.viewModel.ListViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { FeatureAViewModel() }
    viewModel { ListViewModel(fooRepository = get()) }
    viewModel { FormViewModel(fooRepository = get()) }
}

object FeatureAModule {
    fun init() {
        loadKoinModules(viewModelModule)
    }
}