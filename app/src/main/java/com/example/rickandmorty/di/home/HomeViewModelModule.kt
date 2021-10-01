package com.example.rickandmorty.di.home

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.remote.home.HomeRemoteSource
import com.example.rickandmorty.data.remote.home.HomeRemoteSourceImpl
import com.example.rickandmorty.data.repository.home.HomeRepository
import com.example.rickandmorty.data.repository.home.HomeRepositoryImpl
import com.example.rickandmorty.di.keys.ViewModelKey
import com.example.rickandmorty.di.scope.FragmentScope
import com.example.rickandmorty.ui.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @ViewModelKey(HomeViewModel::class)
    @FragmentScope
    @IntoMap
    @Binds
    abstract fun bindsHomeViewModule(
        homeViewModel: HomeViewModel
    ): ViewModel

    @Binds
    @FragmentScope
    abstract fun bindsPageRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @FragmentScope
    abstract fun bindsPageRemoteSource(
        homeRemoteSource: HomeRemoteSourceImpl
    ): HomeRemoteSource
}