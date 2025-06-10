package br.com.pokedex.core.provider.network.provider

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
import androidx.core.content.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

internal class NetworkConnectivityProviderImpl(context: Context) : NetworkConnectivityProvider,
    NetworkCallback() {

    private val connectivityManager = context.getSystemService<ConnectivityManager>()

    private val _isInternetAvailable = MutableLiveData<Boolean>()
    override val isInternetAvailable: LiveData<Boolean> = _isInternetAvailable

    init {
        connectivityManager?.registerDefaultNetworkCallback(this)
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        _isInternetAvailable.postValue(isInternetAvailable())
    }

    private fun isInternetAvailable() = connectivityManager?.let {
        val network = connectivityManager.activeNetwork ?: return false
        connectivityManager.getNetworkCapabilities(network)
            ?.hasCapability(NET_CAPABILITY_VALIDATED) == true
    } ?: true

}