package com.example.rickandmorty.di

import com.example.rickandmorty.ui.viewmodel.Connection
import com.example.rickandmorty.ui.viewmodel.ConnectionImpl
import dagger.Module
import javax.inject.Singleton

@Module
class MainModule {

    @Singleton
    fun providesConnection(): Connection = ConnectionImpl()
}