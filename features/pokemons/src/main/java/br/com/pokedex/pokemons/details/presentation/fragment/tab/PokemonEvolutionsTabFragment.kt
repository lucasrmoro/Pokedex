package br.com.pokedex.pokemons.details.presentation.fragment.tab

import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.pokemons.databinding.FragPokemonEvolutionsTabBinding
import br.com.pokedex.pokemons.details.presentation.viewModel.PokemonEvolutionTabViewModel

internal class PokemonEvolutionsTabFragment private constructor() :
    BaseFragment<FragPokemonEvolutionsTabBinding, PokemonEvolutionTabViewModel>(
        FragPokemonEvolutionsTabBinding::inflate
    ) {

    override fun setupViews() {

    }

    companion object {
        fun newInstance() = PokemonEvolutionsTabFragment()
    }
}