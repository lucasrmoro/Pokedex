package br.com.pokedex.core_network.di

import br.com.pokedex.core_network.BuildConfig
import br.com.pokedex.core_network.authenticator.NetworkAuthenticator
import br.com.pokedex.core_network.data.AuthRemoteDataSource
import br.com.pokedex.core_network.data.AuthRemoteDataSourceImpl
import br.com.pokedex.core_network.data.AuthService
import br.com.pokedex.core_network.ext.retrofitNoAuth
import br.com.pokedex.core_network.ext.setConverterFactory
import br.com.pokedex.core_network.ext.setTimeouts
import br.com.pokedex.core_network.interceptor.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

private val authModule = module {
    factory { retrofitNoAuth.create(AuthService::class.java) }
    factory<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(authService = get()) }
}

private val retrofitModule = module {
    single<NetworkInterceptor> { NetworkInterceptor(preferencesDataSource = get()) }
    single<NetworkAuthenticator> {
        NetworkAuthenticator(
            appSessionProvider = get(),
            authRemoteDataSource = get(),
            preferencesDataSource = get()
        )
    }
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = if (br.com.pokedex.core_network.BuildConfig.DEBUG) Level.BODY else Level.NONE
        }
    }
    single<OkHttpClient>(OkHttpClientAuth) {
        OkHttpClient.Builder()
            .addInterceptor(get<NetworkInterceptor>())
            .authenticator(get<NetworkAuthenticator>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .setTimeouts()
            .build()
    }
    single<Retrofit>(RetrofitAuth) {
        Retrofit.Builder()
            .baseUrl(br.com.pokedex.core_network.BuildConfig.API_BASE_URL)
            .setConverterFactory()
            .client(get<OkHttpClient>(OkHttpClientAuth))
            .build()
    }
    single<OkHttpClient>(OkHttpClientNoAuth) {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .setTimeouts()
            .build()
    }
    single<Retrofit>(RetrofitNoAuth) {
        Retrofit.Builder()
            .baseUrl(br.com.pokedex.core_network.BuildConfig.API_BASE_URL)
            .setConverterFactory()
            .client(get<OkHttpClient>(OkHttpClientNoAuth))
            .build()
    }
}

object CoreNetworkModule {
    fun init() {
        loadKoinModules(listOf(authModule, retrofitModule))
    }
}