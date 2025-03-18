package br.com.pokedex.core_network.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

internal object RetrofitQualifier : Qualifier {

    override val value: QualifierValue = "Retrofit"

}