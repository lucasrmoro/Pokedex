package br.com.pokedex.core_network.di

import br.com.pokedex.core_network.BuildConfig
import br.com.pokedex.core_network.ext.setConverterFactory
import br.com.pokedex.core_network.ext.setTimeouts
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

private val retrofitModule = module {
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
        }
    }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .setTimeouts()
            .build()
    }
    single<Retrofit>(RetrofitQualifier) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .setConverterFactory()
            .client(get<OkHttpClient>())
            .build()
    }
}

object CoreNetworkModule {
    fun init() {
        loadKoinModules(retrofitModule)
    }
}