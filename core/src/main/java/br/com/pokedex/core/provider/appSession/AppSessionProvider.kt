package br.com.pokedex.core.provider.appSession

import androidx.lifecycle.LiveData

interface AppSessionProvider {

    val isSessionExpired: LiveData<Boolean>

    fun expireSession()

}