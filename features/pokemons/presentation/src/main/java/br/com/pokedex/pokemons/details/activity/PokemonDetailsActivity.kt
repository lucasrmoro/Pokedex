package br.com.pokedex.pokemons.details.activity

import android.os.Bundle
import br.com.pokedex.core_ui.base.activity.BaseContainerActivity
import br.com.pokedex.core_ui.ext.extra
import br.com.pokedex.pokemons.details.di.PokemonDetailsModule
import br.com.pokedex.pokemons.details.fragment.PokemonDetailsFragment
import br.com.pokedex.pokemons.details.viewModel.PokemonDetailsViewModel

internal class PokemonDetailsActivity : BaseContainerActivity<PokemonDetailsViewModel>() {

    private val pokemonIdExtra by extra { getInt(POKEMON_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PokemonDetailsModule.init()
        navigate(PokemonDetailsFragment.newInstance(pokemonIdExtra))
    }

    companion object {
        const val POKEMON_ID = "POKEMON_ID"
    }
}