package br.com.pokedex.pokemons.details.fragment

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorRes
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.pokedex.core_ui.adapter.PokedexGenericAdapter
import br.com.pokedex.core_ui.adapter.PokedexViewPagerAdapter
import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.core_ui.ext.attachTo
import br.com.pokedex.core_ui.ext.intArg
import br.com.pokedex.core_ui.ext.putArgs
import br.com.pokedex.core_ui.ext.show
import br.com.pokedex.core_ui.ext.showToast
import br.com.pokedex.pokemons.R
import br.com.pokedex.pokemons.databinding.FragPokemonDetailsBinding
import br.com.pokedex.pokemons.details.fragment.tab.PokemonEvolutionsTabFragment
import br.com.pokedex.pokemons.details.fragment.tab.PokemonMovesTabFragment
import br.com.pokedex.pokemons.details.fragment.tab.PokemonStatsTabFragment
import br.com.pokedex.pokemons.details.viewModel.PokemonDetailsViewModel
import br.com.pokedex.pokemons.model.PokemonDetails
import br.com.pokedex.pokemons.model.PokemonType
import br.com.pokedex.pokemons.model.PokemonTypeItem
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import org.koin.androidx.viewmodel.ext.android.activityViewModel

internal class PokemonDetailsFragment private constructor() :
    BaseFragment<FragPokemonDetailsBinding, PokemonDetailsViewModel>(FragPokemonDetailsBinding::inflate) {

    private val activityViewModel by activityViewModel<PokemonDetailsViewModel>()
    private val pokemonIdArg by intArg(POKEMON_ID)
    private val tabs = listOf(
        PokemonStatsTabFragment.newInstance() to R.string.stats,
        PokemonEvolutionsTabFragment.newInstance() to R.string.evolutions,
        PokemonMovesTabFragment.newInstance() to R.string.moves
    )
    private val viewPagerAdapter: FragmentStateAdapter
        get() = PokedexViewPagerAdapter(this, tabs.map { it.first })

    override fun setupViews() {
        showScreenLoading()
        setupObservers()
        pokemonIdArg?.let(activityViewModel::fetchDetails) ?: run {
            showToast(R.string.unable_to_load_pokemon_details)
            onBackPressed()
            return
        }
        setupViewPager()
    }

    override fun setupObservers() {
        activityViewModel.onFetchDetails.observe(viewLifecycleOwner, ::onFetchDetails)
    }

    private fun onFetchDetails(result: Pair<PokemonDetails?, String?>) = with(binding) {
        result.first?.let {
            it.types.firstOrNull()?.type?.let { type ->
                setupTopBackground(type)
                setupTabLayout(type.startGradientColor)
            }
            ivImage.setImageBitmap(it.image)
            tvPokemonName.text = it.name
            setupRecyclerViewTypes(it.types)
            root.show()
            dismissScreenLoading()
        } ?: run {
            showToast(R.string.unable_to_load_pokemon_details)
            onBackPressed()
        }
    }

    private fun setupTopBackground(pokemonType: PokemonType) = with(pokemonType) {
        context?.let {
            binding.root.background = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(it.getColor(startGradientColor), it.getColor(endGradientColor))
            )
        }
    }

    private fun setupRecyclerViewTypes(types: List<PokemonTypeItem>) = with(binding.rvTypes) {
        layoutManager = FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
            flexWrap = FlexWrap.WRAP
        }
        adapter = PokedexGenericAdapter().apply { submitList(types) }
    }

    private fun setupViewPager() = with(binding) {
        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = tabs.size
        viewPager.attachTo(tabLayout) { tab, position ->
            tab.text = getString(tabs[position].second)
        }
    }

    private fun setupTabLayout(@ColorRes color: Int) = with(binding.tabLayout) {
        context?.let {
            setSelectedTabIndicatorColor(it.getColor(color))
            setTabTextColors(it.getColor(color), Color.WHITE)
        }
    }

    companion object {
        private const val POKEMON_ID = "POKEMON_ID"

        fun newInstance(pokemonId: Int) = PokemonDetailsFragment().putArgs {
            putInt(POKEMON_ID, pokemonId)
        }
    }
}