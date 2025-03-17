package br.com.pokedex.core_network.ext

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun Retrofit.Builder.setConverterFactory(): Retrofit.Builder =
    addConverterFactory(GsonConverterFactory.create())