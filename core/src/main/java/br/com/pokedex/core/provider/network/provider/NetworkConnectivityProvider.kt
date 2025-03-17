package br.com.pokedex.core.provider.network.provider

import androidx.lifecycle.LiveData

interface NetworkConnectivityProvider {

    val isInternetAvailable: LiveData<Boolean>

}