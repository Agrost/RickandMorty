package com.example.rickandmorty.di

import com.example.rickandmorty.data.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {
    private const val URL = "https://rickandmortyapi.com/"

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(URL)
            .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}