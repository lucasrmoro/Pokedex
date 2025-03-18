package br.com.pokedex.core_network.ext

import br.com.pokedex.core_network.di.RetrofitQualifier
import org.koin.core.scope.Scope
import retrofit2.Retrofit

val Scope.retrofit: Retrofit
    get() = get(RetrofitQualifier)