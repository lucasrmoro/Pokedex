package br.com.pokedex.pokemons.details.fragment.tab

import br.com.pokedex.core_ui.adapter.PokedexGenericAdapter
import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.domain_pokemons.R
import br.com.pokedex.pokemons.databinding.FragPokemonStatsTabBinding
import br.com.pokedex.pokemons.details.viewModel.PokemonDetailsViewModel
import br.com.pokedex.pokemons.details.viewModel.PokemonStatsTabViewModel
import br.com.pokedex.pokemons.model.PokemonDetails
import br.com.pokedex.pokemons.model.PokemonStatItem
import org.koin.androidx.viewmodel.ext.android.activityViewModel

internal class PokemonStatsTabFragment private constructor() :
    BaseFragment<FragPokemonStatsTabBinding, PokemonStatsTabViewModel>(FragPokemonStatsTabBinding::inflate) {

    override val applyBackgroundColor: Boolean = false
    private val activityViewModel by activityViewModel<PokemonDetailsViewModel>()
    private val statsAdapter = PokedexGenericAdapter()

    override fun setupViews() = with(binding) {
        rvStats.adapter = statsAdapter
        setupObservers()
    }

    override fun setupObservers() {
        activityViewModel.onFetchDetails.observe(viewLifecycleOwner, ::onFetchDetails)
    }

    private fun onFetchDetails(result: Pair<PokemonDetails?, String?>) {
        result.first?.run {
            statsAdapter.submitList(
                listOf(
                    R.string.hp to hp,
                    R.string.atk to atk,
                    R.string.def to def,
                    R.string.satk to satk,
                    R.string.sdef to sdef,
                    R.string.spd to spd
                ).map {
                    PokemonStatItem(
                        name = getString(it.first),
                        value = it.second,
                        startGradientColor = types.first().type.startGradientColor,
                        endGradientColor = types.first().type.endGradientColor
                    )
                }
            )
        }
    }

    companion object {
        fun newInstance() = PokemonStatsTabFragment()
    }
}