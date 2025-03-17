package br.com.pokedex.core.provider.appSession

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

internal class AppSessionProviderImpl : AppSessionProvider {

    private val _isSessionExpired = MutableLiveData<Boolean>()
    override val isSessionExpired: LiveData<Boolean> = _isSessionExpired

    override fun expireSession() {
        _isSessionExpired.postValue(true)
    }

}