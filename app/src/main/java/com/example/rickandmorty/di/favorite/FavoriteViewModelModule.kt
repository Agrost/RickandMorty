package com.example.rickandmorty.di.favorite

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.keys.ViewModelKey
import com.example.rickandmorty.di.scope.FragmentScope
import com.example.rickandmorty.ui.viewmodel.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteViewModelModule {

    @ViewModelKey(FavoriteViewModel::class)
    @FragmentScope
    @IntoMap
    @Binds
    abstract fun bindsFavoriteViewModule(
        favoriteViewModel: FavoriteViewModel
    ): ViewModel
}