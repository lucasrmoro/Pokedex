package br.com.pokedex.core_network.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

internal object RetrofitNoAuth : Qualifier {

    override val value: QualifierValue = "RetrofitNoAuth"

}

internal object RetrofitAuth : Qualifier {

    override val value: QualifierValue = "Retrofit"

}

internal object OkHttpClientAuth: Qualifier {

    override val value: QualifierValue = "OkHttpClientAuth"

}

internal object OkHttpClientNoAuth: Qualifier {

    override val value: QualifierValue = "OkHttpClientNoAuth"

}