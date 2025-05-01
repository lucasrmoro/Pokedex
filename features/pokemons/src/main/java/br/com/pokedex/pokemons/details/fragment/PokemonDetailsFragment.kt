package br.com.pokedex.pokemons.details.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.pokedex.core_ui.adapter.PokedexViewPagerAdapter
import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.core_ui.ext.attachTo
import br.com.pokedex.core_ui.ext.intArg
import br.com.pokedex.core_ui.ext.putArgs
import br.com.pokedex.core_ui.ext.showToast
import br.com.pokedex.pokemons.R
import br.com.pokedex.pokemons.databinding.FragPokemonDetailsBinding
import br.com.pokedex.pokemons.details.fragment.tab.PokemonEvolutionsTabFragment
import br.com.pokedex.pokemons.details.fragment.tab.PokemonMovesTabFragment
import br.com.pokedex.pokemons.details.fragment.tab.PokemonStatsTabFragment
import br.com.pokedex.pokemons.details.viewModel.PokemonDetailsViewModel

internal class PokemonDetailsFragment private constructor() :
    BaseFragment<FragPokemonDetailsBinding, PokemonDetailsViewModel>(FragPokemonDetailsBinding::inflate) {

    private val pokemonIdArg by intArg(POKEMON_ID)
    private val tabs = listOf(
        PokemonStatsTabFragment.newInstance() to R.string.stats,
        PokemonEvolutionsTabFragment.newInstance() to R.string.evolutions,
        PokemonMovesTabFragment.newInstance() to R.string.moves
    )
    private val viewPagerAdapter: FragmentStateAdapter
        get() = PokedexViewPagerAdapter(this, tabs.map(Pair<Fragment, Int>::first))

    override fun setupViews() {
        showScreenLoading()
        pokemonIdArg?.let(viewModel::fetchDetails) ?: run {
            showToast(R.string.unable_to_load_pokemon_details)
            onBackPressed()
            return
        }
        setupViewPager()
    }

    private fun setupViewPager() = with(binding) {
        viewPager.adapter = viewPagerAdapter
        viewPager.attachTo(tabLayout) { tab, position ->
            tab.text = getString(tabs[position].second)
        }
        viewPager.offscreenPageLimit = tabs.size
    }

    companion object {
        private const val POKEMON_ID = "POKEMON_ID"

        fun newInstance(pokemonId: Int) = PokemonDetailsFragment().putArgs {
            putInt(POKEMON_ID, pokemonId)
        }
    }
}