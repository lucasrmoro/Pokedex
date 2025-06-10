package br.com.pokedex.pokemons.details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core.ext.onError
import br.com.pokedex.core.ext.onSuccess
import br.com.pokedex.pokemons.model.PokemonAbilityItem
import br.com.pokedex.pokemons.useCase.ability.GetPokemonAbilityUseCase
import kotlinx.coroutines.flow.onCompletion

internal class PokemonStatsTabViewModel(
    private val getPokemonAbilityUseCase: GetPokemonAbilityUseCase
) : BaseViewModel() {

    private val _onGetAbilitiesDescription = MutableLiveData<List<PokemonAbilityItem>>()
    val onGetAbilitiesDescription: LiveData<List<PokemonAbilityItem>> = _onGetAbilitiesDescription

    fun getAbilitiesDescriptions(abilities: List<PokemonAbilityItem>) {
        var mAbilities = abilities
        abilities.forEach { ability ->
            launch {
                getPokemonAbilityUseCase(ability.ability.name).onError {
                    mAbilities = mAbilities.toMutableList().apply {
                        removeIf { it.ability.name == ability.ability.name }
                    }.toList()
                }.onCompletion {
                    _onGetAbilitiesDescription.value = (mAbilities)
                }.onSuccess {
                    mAbilities = mAbilities.map {
                        val description =
                            if (it.ability.name == name) description else it.ability.description
                        it.copy(ability = it.ability.copy(description = description))
                    }
                }
            }
        }
    }

}