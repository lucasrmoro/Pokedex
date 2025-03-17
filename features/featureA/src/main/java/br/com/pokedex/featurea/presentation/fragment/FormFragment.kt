package br.com.pokedex.featurea.presentation.fragment

import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.featurea.databinding.FragFormBinding
import br.com.pokedex.featurea.presentation.viewModel.FormViewModel

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