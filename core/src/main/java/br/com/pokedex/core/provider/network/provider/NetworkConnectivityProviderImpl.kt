package br.com.pokedex.core.provider.network.provider

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pokedex.core.ext.FIVE
import br.com.pokedex.core.ext.debounce
import java.util.concurrent.TimeUnit

internal class NetworkConnectivityProviderImpl(context: Context) : NetworkConnectivityProvider,
    NetworkCallback() {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NET_CAPABILITY_INTERNET)
        .addTransportType(TRANSPORT_WIFI)
        .addTransportType(TRANSPORT_CELLULAR)
        .build()

    private val _isInternetAvailable =
        MutableLiveData(connectivityManager.activeNetwork.isInternetAvailable())
    override val isInternetAvailable: LiveData<Boolean> =
        _isInternetAvailable.debounce(Long.FIVE, TimeUnit.SECONDS)

    init {
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }

    override fun onAvailable(network: Network) {
        _isInternetAvailable.postValue(network.isInternetAvailable())
    }

    override fun onLost(network: Network) {
        _isInternetAvailable.postValue(network.isInternetAvailable())
    }

    private fun Network?.isInternetAvailable() = this?.let {
        connectivityManager.getNetworkCapabilities(it)
            ?.hasCapability(NET_CAPABILITY_INTERNET) == true
    } ?: false

}