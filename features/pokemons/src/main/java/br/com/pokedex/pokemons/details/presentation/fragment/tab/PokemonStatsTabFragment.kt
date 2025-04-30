package br.com.pokedex.pokemons.details.presentation.fragment.tab

import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.pokemons.databinding.FragPokemonMovesTabBinding
import br.com.pokedex.pokemons.details.presentation.viewModel.PokemonStatsTabViewModel

internal class PokemonStatsTabFragment private constructor() :
    BaseFragment<FragPokemonMovesTabBinding, PokemonStatsTabViewModel>(FragPokemonMovesTabBinding::inflate) {

    override fun setupViews() {

    }

    companion object {
        fun newInstance() = PokemonStatsTabFragment()
    }
}