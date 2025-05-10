package br.com.pokedex.pokemons.details.fragment.tab

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.pokedex.core_ui.adapter.PokedexGenericAdapter
import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.domain.adapter.AdapterItem
import br.com.pokedex.domain_pokemons.R
import br.com.pokedex.pokemons.databinding.FragPokemonStatsTabBinding
import br.com.pokedex.pokemons.details.viewModel.PokemonDetailsViewModel
import br.com.pokedex.pokemons.details.viewModel.PokemonStatsTabViewModel
import br.com.pokedex.pokemons.model.PokemonDetails
import br.com.pokedex.pokemons.model.PokemonStatItem
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import org.koin.androidx.viewmodel.ext.android.activityViewModel

internal class PokemonStatsTabFragment private constructor() :
    BaseFragment<FragPokemonStatsTabBinding, PokemonStatsTabViewModel>(FragPokemonStatsTabBinding::inflate) {

    override val applyBackgroundColor: Boolean = false
    private val activityViewModel by activityViewModel<PokemonDetailsViewModel>()
    private val statsAdapter = PokedexGenericAdapter()
    private val weaknessesAdapter = PokedexGenericAdapter()
    private val resistancesAdapter = PokedexGenericAdapter()
    private val immunitiesAdapter = PokedexGenericAdapter()

    override fun setupViews() = with(binding) {
        rvStats.adapter = statsAdapter
        setupObservers()
    }

    override fun setupObservers() {
        activityViewModel.onFetchDetails.observe(viewLifecycleOwner, ::onFetchDetails)
    }

    private fun onFetchDetails(result: Pair<PokemonDetails?, String?>) {
        result.first?.run {
            setupColors()
            setupStats()
            setupWeaknesses()
            setupResistances()
            setupImmunities()
        }
    }

    private fun PokemonDetails.setupStats() {
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

    private fun PokemonDetails.setupColors() = with(binding) {
        listOf(tvWeaknesses, tvResistances, tvImmunities).forEach { textView ->
            context?.let {
                textView.setTextColor(it.getColor(types.first().type.startGradientColor))
            }
        }
    }

    private fun PokemonDetails.setupWeaknesses() = with(binding) {
        setupAttributes(tvWeaknesses, rvWeaknesses, weaknesses, weaknessesAdapter)
    }

    private fun PokemonDetails.setupResistances() = with(binding) {
        setupAttributes(tvResistances, rvResistances, resistances, resistancesAdapter)
    }

    private fun PokemonDetails.setupImmunities() = with(binding) {
        setupAttributes(tvImmunities, rvImmunities, immunities, immunitiesAdapter)
    }

    private fun setupAttributes(
        tvAttribute: TextView,
        rvAttribute: RecyclerView,
        attributeList: List<AdapterItem>,
        attributeAdapter: PokedexGenericAdapter
    ) {
        tvAttribute.isVisible = attributeList.isNotEmpty()
        rvAttribute.adapter = attributeAdapter
        rvAttribute.layoutManager = FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        attributeAdapter.submitList(attributeList)
    }

    companion object {
        fun newInstance() = PokemonStatsTabFragment()
    }
}