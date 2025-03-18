package br.com.pokedex.pokemons.list.presentation.fragment

import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.pokemons.list.presentation.viewModel.FormViewModel
import br.com.pokedex.pokemonsList.databinding.FragFormBinding

internal class FormFragment private constructor() :
    BaseFragment<FragFormBinding, FormViewModel>(FragFormBinding::inflate) {

    override fun setupViews() {
        setupListeners()
    }

    override fun setupListeners() = with(binding) {
        btnSave.setOnClickListener {
            btnSave.isLoading = true
            viewModel.insert(etMessage.text.orEmpty())
            popBackStack()
        }
    }

    companion object {
        fun newInstance() = FormFragment()
    }
}