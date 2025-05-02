package br.com.pokedex.pokemons.details.fragment.tab

import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.pokemons.databinding.FragPokemonEvolutionsTabBinding
import br.com.pokedex.pokemons.details.viewModel.PokemonEvolutionTabViewModel

internal class PokemonEvolutionsTabFragment private constructor() :
    BaseFragment<FragPokemonEvolutionsTabBinding, PokemonEvolutionTabViewModel>(
        FragPokemonEvolutionsTabBinding::inflate
    ) {

    override val applyBackgroundColor: Boolean = false

    override fun setupViews() {

    }

    companion object {
        fun newInstance() = PokemonEvolutionsTabFragment()
    }
}