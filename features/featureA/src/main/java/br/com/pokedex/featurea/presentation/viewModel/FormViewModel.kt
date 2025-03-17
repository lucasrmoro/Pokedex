package br.com.pokedex.featurea.presentation.viewModel

import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core_ui.adapter.model.Foo
import br.com.pokedex.domain.repository.foo.FooRepository

class FormViewModel(private val fooRepository: FooRepository) : BaseViewModel() {

    fun insert(message: String) {
        launch {
            fooRepository.insert(Foo(msg = message))
        }
    }

}