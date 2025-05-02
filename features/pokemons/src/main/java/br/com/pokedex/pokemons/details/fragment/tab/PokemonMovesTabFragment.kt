package br.com.pokedex.pokemons.details.fragment.tab

import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.pokemons.databinding.FragPokemonMovesTabBinding
import br.com.pokedex.pokemons.details.viewModel.PokemonMovesTabViewModel

internal class PokemonMovesTabFragment private constructor() :
    BaseFragment<FragPokemonMovesTabBinding, PokemonMovesTabViewModel>(FragPokemonMovesTabBinding::inflate) {

    override val applyBackgroundColor: Boolean = false

    override fun setupViews() {

    }

    companion object {
        fun newInstance() = PokemonMovesTabFragment()
    }
}