package br.com.pokedex.pokemons.details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core.ext.onError
import br.com.pokedex.core.ext.onSuccess
import br.com.pokedex.pokemons.model.PokemonDetails
import br.com.pokedex.pokemons.useCase.details.GetPokemonDetailsUseCase

internal class PokemonDetailsViewModel(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : BaseViewModel() {

    private val _onFetchDetails = MutableLiveData<Pair<PokemonDetails?, String?>>()
    val onFetchDetails: LiveData<Pair<PokemonDetails?, String?>> = _onFetchDetails

    fun fetchDetails(pokemonId: Int) {
        launch {
            getPokemonDetailsUseCase(pokemonId).onError {
                _onFetchDetails.postValue(Pair(null, message))
            }.onSuccess {
                _onFetchDetails.postValue(Pair(this, null))
            }
        }
    }

}