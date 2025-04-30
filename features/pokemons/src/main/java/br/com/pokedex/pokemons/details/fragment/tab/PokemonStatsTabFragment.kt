package br.com.pokedex.pokemons.details.fragment.tab

import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.pokemons.databinding.FragPokemonMovesTabBinding
import br.com.pokedex.pokemons.details.viewModel.PokemonStatsTabViewModel

internal class PokemonStatsTabFragment private constructor() :
    BaseFragment<FragPokemonMovesTabBinding, PokemonStatsTabViewModel>(FragPokemonMovesTabBinding::inflate) {

    override fun setupViews() {

    }

    companion object {
        fun newInstance() = PokemonStatsTabFragment()
    }
}